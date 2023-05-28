package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Exeptions.NumberNotFoundExeption;
import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.StationVisual;
import Graphics.Window;
import InterfaceLink.StationLink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Reprezentuje stację w systemie komunikacyjnym.
 */
public class Station implements StationLink, Runnable, Comparable<Station> {

    private static int counterId; // Licznik identyfikatorów stacji
    private final int Id; // Identyfikator stacji
    private int processedMessageCounter; // Licznik przetworzonych wiadomości
    private int waitingMessageCounter; // Licznik oczekujących wiadomości
    private final StationType type; // Typ stacji
    private final ArrayList<Message> messagesInDeckList; // Lista wiadomości oczekujących na przetworzenie
    private final Thread refreshThread; // Wątek odświeżający
    private final ArrayList<RefreshListner> listeners = new ArrayList<>(); // Lista słuchaczy odświeżania
    private boolean working; // Flaga określająca, czy stacja jest aktywna


    /**
     * Tworzy nową instancję stacji o określonym typie.
     *
     * @param type Typ stacji (BSC lub BTS)
     */
    public Station(StationType type) {
        this.working = true;
        this.type = type;
        counterId++;
        Id = counterId;
        processedMessageCounter = 0;
        messagesInDeckList = new ArrayList<>();
        waitingMessageCounter = 0;

        // Tworzenie i uruchamianie głównego wątku
        Thread thread = new Thread(this);
        thread.start();

        // Tworzenie i uruchamianie wątku odświerzającego
        refreshThread = new Thread(this::refreshThreadLoop);
        refreshThread.start();
    }

    /**
     * Uruchamia wątek stacji, który cyklicznie przetwarza wiadomości.
     */
    @Override
    public void run() {
        while (this.working) {
            Random random = new Random();
            int sleepTime;

            if (this.getType() == StationType.BSC) {
                //Określenie czasu oczekiwania dla stacji typu BSC
                // (Losowy czas od 5 do 15 sekund).
                sleepTime = random.nextInt(11) + 5;
            } else {
                //Określenie czasu oczekiwania dla stacji typu BTS (3 sekundy).
                sleepTime = 3;
            }

            try {
                synchronized (this) {
                    wait(sleepTime * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message currentMessage = null;
            for (Message value : messagesInDeckList) {
                if (value != null) {
                    currentMessage = value;
                    break;
                }
            }
            Station nextStation = findNextStation();
            if (nextStation != null && currentMessage != null) {
                for (Message message : messagesInDeckList) {
                    if (message != null) {
                        currentMessage = message;
                        break;
                    }
                }
                nextStation.reciveMessage(currentMessage);
                messagesInDeckList.remove(currentMessage);
                waitingMessageCounter = messagesInDeckList.size();
                processedMessageCounter++;
            } else if (nextStation == null && currentMessage != null) {
                try {
                    sendMessageToVRD(currentMessage);
                    messagesInDeckList.remove(currentMessage);
                    waitingMessageCounter = messagesInDeckList.size();
                    processedMessageCounter++;
                } catch (NumberNotFoundExeption e) {
                    Window.showInfoDialog("VRD Not Found", "Nie odnaleziono obektu VRD", null);
                }
            }

        }

    }

    /**
     * Wysyła wiadomość do obiektu VRD o określonym adresie.
     *
     * @param message Wiadomość do wysłania
     * @throws NumberNotFoundExeption Jeśli nie odnaleziono obiektu VRD o podanym adresie
     */
    public void sendMessageToVRD(Message message) throws NumberNotFoundExeption {
        int adress = message.getAdress();
        boolean messageSended = false;
        for (int i = 0; i < Window.VRDlist.size(); i++) {
            if ((Window.VRDlist.get(i).getID()) == adress) {
                if (Window.VRDlist.get(i).getIsWorking()) {
                    Window.VRDlist.get(i).reciveMessage(message);
                    messageSended = true;
                }
                break;
            }
        }
        if (!messageSended) {
            throw new NumberNotFoundExeption(adress);
        }
    }

    /**
     * Odbiera wiadomość i przetwarza ją w stacji.
     *
     * @param message Wiadomość do odebrania i przetworzenia
     */
    @Override
    public void reciveMessage(Message message) {
        if (messagesInDeckList.size() < 5) {
            messagesInDeckList.add(message);
            waitingMessageCounter = messagesInDeckList.size();
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        } else {
            createNewStation(message);
        }
    }

    /**
     * Znajduje następną stację w warstwie.
     *
     * @return Następna stacja lub null, jeśli nie ma kolejnej stacji
     */
    @Override
    public Station findNextStation() {
        LayerVisual nextLayer = findNextLayer();
        if (nextLayer != null) {
            return Collections.min(nextLayer.stationList);
        } else {
            return null;
        }
    }

    /**
     * Znajduje następną warstwę.
     *
     * @return Następna warstwa lub null, jeśli nie ma kolejnej warstwy
     */
    @Override
    public LayerVisual findNextLayer() {
        for (int i = 0; i < Window.layers.size(); i++) {
            if (Window.layers.get(i).containsStation(this)) {
                if (i + 1 < Window.layers.size()) {
                    return Window.layers.get(i + 1);
                }
            }
        }
        return null;
    }

    /**
     * Tworzy nową stację w bieżącej warstwie i przesyła do niej wiadomość.
     *
     * @param message Wiadomość do przesłania do nowej stacji
     */
    @Override
    public void createNewStation(Message message) {
        LayerVisual CurrentLayer = null;
        for (int i = 0; i < Window.layers.size(); i++) {
            if (Window.layers.get(i).containsStation(this)) {
                CurrentLayer = Window.layers.get(i);
                break;
            }
        }
        if (CurrentLayer != null) {
            Station newStation = new Station(type);
            StationVisual newStationVisual = new StationVisual(newStation);
            newStation.reciveMessage(message);
            newStation.addRefreshListener(newStationVisual);
            CurrentLayer.stationList.add(newStation);
            CurrentLayer.layerViewPort.add(newStationVisual);
            CurrentLayer.revalidate();
            CurrentLayer.repaint();
        }
    }

    /**
     * "Wyłącza" stacje wysyłając wszystkie wiadomości z pominięciem delaya
     */
    @Override
    public void turnOff() {
        this.setIsWorking(false);
        for (int i = 0; i < messagesInDeckList.size(); i++) {
            Station nextStation = findNextStation();
            Message currentMessage = messagesInDeckList.get(i);
            if (nextStation != null && currentMessage != null) {
                for (Message message : messagesInDeckList) {
                    if (message != null) {
                        currentMessage = message;
                        break;
                    }
                }
                nextStation.reciveMessage(currentMessage);
                messagesInDeckList.remove(currentMessage);
                waitingMessageCounter = messagesInDeckList.size();
                processedMessageCounter++;
            } else if (nextStation == null && currentMessage != null) {
                try {
                    sendMessageToVRD(currentMessage);
                    messagesInDeckList.remove(currentMessage);
                    waitingMessageCounter = messagesInDeckList.size();
                    processedMessageCounter++;
                } catch (NumberNotFoundExeption e) {
                    Window.showInfoDialog("VRD Not Found", "Nie odnaleziono obektu VRD", null);
                }
            }
        }
    }

    /**
     * Pętla wątku odświeżającego.
     * Wywołuje zdarzenie odświeżenia dla słuchaczy.
     */
    private void refreshThreadLoop() {
        while (refreshThread.isAlive()) {
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        }
    }

    /**
     * Wywołuje zdarzenie odświeżenia dla zarejestrowanych słuchaczy.
     *
     * @param event Zdarzenie odświeżenia
     */
    private void fireRefresh(RefreshEvent event) {
        synchronized (listeners){
            for (RefreshListner listener : listeners) {
                listener.refresh(event);
            }
        }
    }

    /**
     * Dodaje słuchacza odświeżenia.
     *
     * @param listener Słuchacz odświeżenia do dodania
     */
    public void addRefreshListener(RefreshListner listener) {
        listeners.add(listener);
    }

    /**
     * Porównuje bieżącą stację z inną stacją na podstawie liczby oczekujących wiadomości.
     *
     * @param other Inna stacja do porównania
     * @return Wartość ujemna, zero lub wartość dodatnia, jeśli bieżąca stacja jest mniejsza, równa lub większa od innej stacji
     */
    @Override
    public int compareTo(Station other) {
        return Integer.compare(this.waitingMessageCounter, other.waitingMessageCounter);
    }

    /**
     * Zwraca identyfikator stacji.
     *
     * @return Identyfikator stacji
     */
    @Override
    public int getId() {
        return Id;
    }

    /**
     * Zwraca liczbę przetworzonych wiadomości.
     *
     * @return Liczba przetworzonych wiadomości
     */
    @Override
    public int getProcessedMessageCounter() {
        return processedMessageCounter;
    }

    /**
     * Zwraca liczbę oczekujących wiadomości.
     *
     * @return Liczba oczekujących wiadomości
     */

    @Override
    public int getWaitingMessageCounter() {
        return waitingMessageCounter;
    }

    /**
     * Zwraca typ stacji.
     *
     * @return Typ stacji
     */
    @Override
    public StationType getType() {
        return type;
    }

    /**
     * Ustawia stan pracy stacji.
     *
     * @param bol True, jeśli stacja ma pracować; false w przeciwnym razie
     */
    @Override
    public void setIsWorking(boolean bol) {
        this.working = bol;
    }

    /**
     * Zwraca reprezentację tekstową stacji.
     *
     * @return Reprezentacja tekstowa stacji
     */
    @Override
    public String toString() {
        return "Station{" +
                "Id=" + Id +
                ", processedMessageCounter=" + processedMessageCounter +
                ", waitingMessageCounter=" + waitingMessageCounter +
                ", type='" + type.toString() + '\'' +
                ", messagesInDeckList=" + messagesInDeckList +
                '}';
    }
}
