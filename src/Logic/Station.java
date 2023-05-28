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

//2do:

public class Station implements StationLink,Runnable, Comparable<Station> {

    private static int counterId;
    private int Id;
    private int processedMessageCounter;
    private int waitingMessageCounter;
    private StationType type;
    private ArrayList<Message> messagesInDeckList; //Oczekujące Wiadomości
    private Message currentMessage;
    private Thread thread; //Główny wątek
    private Thread refreshThread; //Poboczny wątek służący do odświerzania
    private ArrayList<RefreshListner> listeners = new ArrayList<>();
    private boolean working;




    public Station(StationType type) {
        this.working = true;
        this.type = type;
        counterId++;
        Id=counterId;
        System.out.println("DEBUG: UTWORZONO STACJE: " + this.getId());
        processedMessageCounter = 0;
        messagesInDeckList = new ArrayList<Message>();
        waitingMessageCounter = messagesInDeckList.size();
        // Tworzenie i uruchamianie głównego wątku
        thread = new Thread(this);
        thread.start();
        // Tworzenie i uruchamianie wątku odświerzającego
        refreshThread = new Thread(this::refreshThreadLoop);
        refreshThread.start();
    }

    @Override
    public int getId() {
        return Id;
    }


    @Override
    public int getProcessedMessageCounter() {
        return processedMessageCounter;
    }

    @Override
    public int getWaitingMessageCounter() {
        return waitingMessageCounter;
    }

    @Override
    public StationType getType() {
        return type;
    }

    @Override
    public Message getCurrentMessage() {
        return currentMessage;
    }
    @Override
    public boolean getIsWorking() {
        return working;
    }
    @Override
    public void setIsWorking(boolean bol) {
        System.out.println("Debug: Stacja: " + this + " wyłączono ");
        this.working = bol;
    }

    @Override
    public LayerVisual findNextLayer() {
        for (int i = 0; i < Window.layers.size(); i++) {
            if(Window.layers.get(i).containsStation(this)){
                if(i + 1 < Window.layers.size()) {
                    return Window.layers.get(i+1);
                }
            }
        }
        return null;
    }
    @Override
    public Station findNextStation() {
        LayerVisual nextLayer = findNextLayer();
        if(nextLayer != null){
            return Collections.min(nextLayer.stationList);
        }else {
            return null;
        }
    }
    @Override
    public void reciveMessage(Message message) {
        if(messagesInDeckList.size() < 5) {
            messagesInDeckList.add(message);
            waitingMessageCounter = messagesInDeckList.size();
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        }else {
            createNewStation(message);
        }
    }
    @Override
    public void createNewStation(Message message) {
        //2DO
        LayerVisual CurrentLayer = null;
        for (int i = 0; i < Window.layers.size(); i++) {
            if (Window.layers.get(i).containsStation(this)) {
               CurrentLayer = Window.layers.get(i);
               break;
            }
        }
        if(CurrentLayer != null) {
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

    @Override
    public int compareTo(Station other) {
        return Integer.compare(this.waitingMessageCounter, other.waitingMessageCounter);
    }
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


    @Override
    public void run() {
        while(this.working) {
            Random random = new Random();
            int sleepTime;

            if(this.getType() == StationType.BSC) {
                //BSC
                sleepTime = random.nextInt(11) + 5; //Losowy Czas od 5 do 15 sekund
            }else  {
                //BTS
                sleepTime = 3; //3 Sekundy
            }

            try {
                Thread.sleep(sleepTime * 1000); // Zamiana na milisekundy
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentMessage = null;
            for (int i = 0; i < messagesInDeckList.size(); i++) {
                if(messagesInDeckList.get(i) != null) {
                    currentMessage = messagesInDeckList.get(i);
                    break;
                }
            }
            Station nextStation = findNextStation();
            if(nextStation != null && currentMessage != null) {
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
            }else if (nextStation == null && currentMessage != null) {
                try {
                   sendMessageToVRD(currentMessage);
                   messagesInDeckList.remove(currentMessage);
                   waitingMessageCounter = messagesInDeckList.size();
                   processedMessageCounter++;
                } catch (NumberNotFoundExeption e) {
                    Window.showInfoDialog("VRD Not Found","Nie odnaleziono obektu VRD",null);
                }
            }

        }

    }
    private void refreshThreadLoop() {
        while (refreshThread.isAlive()) {
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        }
    }
    private void fireRefresh(RefreshEvent event) {
        for (RefreshListner listener : listeners) {
            listener.refresh(event);
        }
    }
    public void addRefreshListener(RefreshListner listener) {
        listeners.add(listener);
    }

    public void removeRefreshListener(RefreshListner listener) {
        listeners.remove(listener);
    }
    public void sendMessageToVRD(Message message) throws NumberNotFoundExeption {
        int adress = message.getAdress();
        boolean messageSended = false;
        for (int i = 0; i < Window.VRDlist.size(); i++) {
            if((Window.VRDlist.get(i).getID()) == adress) {
                if(Window.VRDlist.get(i).getIsWorking()) {
                    Window.VRDlist.get(i).reciveMessage(message);
                    messageSended = true;
                }
                break;
            }
        }
        if(!messageSended) {
            throw new NumberNotFoundExeption(adress);
        }
    }
}
