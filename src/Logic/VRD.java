package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Graphics.Window;
import InterfaceLink.VRDlink;

import java.util.ArrayList;

/**
 * Klasa reprezentująca urządzenie VRD.
 * Implementuje interfejsy VRDlink i Runnable.
 */
public class VRD implements VRDlink, Runnable {
    private final int Id;
    private int receivedMessageCounter;
    private boolean isWorking;
    private Thread refreshThread;
    private final ArrayList<RefreshListner> listners = new ArrayList<>();
    private boolean resetMesagesCounter;

    /**
     * Konstruktor inicjalizujący obiekt VRD.
     * Tworzy wątek główny.
     */
    public VRD() {

        this.isWorking = true;
        this.receivedMessageCounter = 0;
        Window.phoneNumbers ++;
        this.Id = Window.phoneNumbers;
        resetMesagesCounter = false;

        // Tworzenie i uruchamianie głównego wątku
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Metoda run() interfejsu Runnable.
     * Główna pętla wątku VRD.
     */
    @Override
    public void run() {
        while (this.isWorking) {
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        }
    }


    /**
     * Metoda interfejsu VRDlink.
     * Obsługa odebranej wiadomości.
     */
    @Override
    public void reciveMessage(Message message) {
        receivedMessageCounter++;
        RefreshEvent refreshEvent = new RefreshEvent(this, this);
        fireRefresh(refreshEvent);
    }

    /**
     * Metoda interfejsu VRDlink.
     * Uruchamianie wątku odświeżającego licznik wiadomości.
     */
    @Override
    public void startResetMessageCount() {
        // Tworzenie i uruchamianie wątku odświerzającego
        resetMesagesCounter = true;
        refreshThread = new Thread(this::messegageCounterRefreshLoop);
        refreshThread.start();
    }

    /**
     * Metoda interfejsu VRDlink.
     * Zatrzymywanie wątku odświeżającego licznik wiadomości.
     */
    @Override
    public void stopResetMessageCount() {
        resetMesagesCounter = false;
    }

    /**
     * Metoda prywatna.
     * Pętla wątku odświeżającego licznik wiadomości.
     */
    private void messegageCounterRefreshLoop() {
        while (refreshThread.isAlive()) {
            try {
                synchronized (this) {
                    wait(10000);//10 sekund
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (resetMesagesCounter) {
                this.receivedMessageCounter = 0;
            }
        }
    }

    /**
     * Metoda prywatna.
     * Wywołuje zdarzenie odświeżenia dla słuchaczy.
     */
    private void fireRefresh(RefreshEvent refreshEvent) {
        for (RefreshListner listener : listners) {
            listener.refresh(refreshEvent);
        }
    }

    /**
     * Metoda publiczna.
     * Dodaje słuchacza zdarzeń odświeżenia.
     */
    public void addRefreshListner(RefreshListner listner) {
        this.listners.add(listner);
    }

    /**
     * Metoda interfejsu VRDlink.
     * Zwraca identyfikator VRD.
     */
    @Override
    public int getID() {
        return Id;
    }

    /**
     * Metoda interfejsu VRDlink.
     * Zwraca informację o statusie pracy VRD.
     */
    @Override
    public boolean getIsWorking() {
        return isWorking;
    }

    /**
     * Metoda interfejsu VRDlink.
     * Zwraca licznik odebranych wiadomości.
     */
    @Override
    public int getReceivedMessageCount() {
        return receivedMessageCounter;
    }


    /**
     * Metoda interfejsu VRDlink.
     * Ustawia status pracy VRD.
     */
    @Override
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }


}
