package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Exeptions.NumberNotFoundExeption;

import Graphics.Window;
import InterfaceLink.VBDlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Klasa VBD reprezentuje urządzenie VBD.
 * Implementuje interfejs VBDlink i Runnable.
 */
public class VBD implements VBDlink, Runnable {
    private Message message;
    private int frequency;
    private boolean isWorking;
    private boolean isWaiting;
    private final int Id;
    private int messageSendedCounter;
    private final ArrayList<RefreshListner> listners = new ArrayList<>();

    /**
     * Konstruktor klasy VBD.
     *
     * @param message wiadomość do przesyłania
     */
    public VBD(Message message) {

        Window.phoneNumbers++;
        this.message = message;
        this.frequency = 1;
        this.isWorking = true;
        this.isWaiting = false;
        this.Id = Window.phoneNumbers;

        // Tworzenie i uruchamianie wątku
        Thread thread = new Thread(this);
        thread.start();
    }



    /**
     * Metoda losująca numer VRD.
     *
     * @return wylosowany numer VRD
     */
    @Override
    public int randomVRD() {
        if (Window.VRDlist == null || Window.VRDlist.isEmpty()) {
            try {
                throw new NumberNotFoundExeption(0);
            } catch (NumberNotFoundExeption nnfe) {
                Window.showInfoDialog("VRD Not Found", "VBD: " + this.getID() + "\n" +
                        "Nie odnalazł dostępnego VRD.\n"
                        + "VBD przechodzi w stan oczekiwania.", null);
                System.out.println("VBD: " + this.getID() + " Nie odnalazł dostępnego VRD. "
                        + "VBD przechodzi w stan oczekiwania.");
            }
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(Window.VRDlist.size());
            VRD tempVRD = Window.VRDlist.get(randomIndex);

            return tempVRD.getID();
        }
        return 0; //nie zaistnieje VRD o takim ID
    }

    /**
     * Metoda wysyłająca wiadomość do stacji docelowej.
     *
     * @param message wiadomość do wysłania
     */
    @Override
    public void sendMessage(Message message) {
        Station nextStation = findMostEmptyStation();
        nextStation.reciveMessage(message);
        messageSendedCounter++;

    }

    /**
     * Metoda znajdująca stację o najmniejszej liczbie wiadomości.
     *
     * @return stacja o najmniejszej liczbie wiadomości
     */
    @Override
    public Station findMostEmptyStation() {
        Station mostEmptyStation;

        if (!(Window.layers.get(0).stationList.isEmpty())) {
            mostEmptyStation = Collections.min(Window.layers.get(0).stationList);
        } else {
            return null;
        }
        return mostEmptyStation;
    }

    /**
     * Metoda reprezentująca wątek działający dla VBD.
     */
    @Override
    public void run() {
        while (isWorking) {
            if (isWaiting) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                int VRDnumber = randomVRD();
                if (VRDnumber != 0) {

                    message.setAdress(VRDnumber);
                    sendMessage(this.getMessage());
                } else {
                    fireRefresh(new RefreshEvent(this, this));
                }
            }
            try {
                synchronized (this) {
                    wait(1000 / frequency);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda wywołująca zdarzenie odświeżenia.
     *
     * @param refreshEvent zdarzenie odświeżenia
     */
    private void fireRefresh(RefreshEvent refreshEvent) {
        for (RefreshListner listener : listners) {
            listener.refresh(refreshEvent);
        }
    }

    /**
     * Metoda dodająca słuchacza odświeżenia.
     *
     * @param listner słuchacz odświeżenia
     */
    public void addRefreshListner(RefreshListner listner) {
        this.listners.add(listner);
    }

    /**
     * Ustawia wiadomość dla VBD.
     *
     * @param message wiadomość do ustawienia
     */
    @Override
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Ustawia częstotliwość dla VBD.
     *
     * @param frequency częstotliwość do ustawienia
     */
    @Override
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Ustawia stan pracy VBD.
     *
     * @param working stan pracy do ustawienia
     */
    @Override
    public void setIsWorking(boolean working) {
        this.isWorking = working;
    }

    /**
     * Ustawia stan oczekiwania VBD.
     *
     * @param waiting stan oczekiwania do ustawienia
     */
    @Override
    public void setIsWaiting(boolean waiting) {
        this.isWaiting = waiting;
    }

    /**
     * Zwraca aktualną wiadomość VBD.
     *
     * @return aktualna wiadomość VBD
     */
    @Override
    public Message getMessage() {
        return message;
    }

    /**
     * Zwraca aktualną częstotliwość VBD.
     *
     * @return aktualna częstotliwość VBD
     */
    @Override
    public int getFrequency() {
        return frequency;
    }

    /**
     * Zwraca identyfikator VBD.
     *
     * @return identyfikator VBD
     */
    @Override
    public int getID() {
        return Id;
    }

    /**
     * Zwraca ilośc wysłanych wiadomości przez VBD
     * @return ilośc wysłanych wiadomości przez VBD
     */
    public int getMessageSendedCounter() {
        return messageSendedCounter;
    }
}
