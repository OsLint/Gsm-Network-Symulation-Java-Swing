package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.VRDlink;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class VRD implements VRDlink, Runnable {
    //Pola Prywatne
    private static int countId;
    private int Id;
    private int receivedMessageCounter;
    private boolean isWorking;
    private Thread thread; //Główny wątek (odświerzający Visual)
    private Thread refreshThread; //Wątek odświerzania countera
    private ArrayList<RefreshListner> listners = new ArrayList<>();

    //Konstruktor
    public VRD () {
        countId++;
        this.isWorking = true;
        this.receivedMessageCounter = 0;
        this.Id = countId;

        // Tworzenie i uruchamianie głównego wątku
        thread = new Thread(this);
        thread.start();


    }



    //gettery
    @Override
    public int getID() {
        return Id;
    }

    @Override
    public boolean getIsWorking() {
        return isWorking;
    }

    @Override
    public boolean getResetMessageCount() {
        return false;
    }
    @Override
    public int getReceivedMessageCount() {
        return receivedMessageCounter;
    }
    //settery
    @Override
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    //Nadpisane metody z interfejsu

    @Override
    public void reciveMessage(Message message) {
        receivedMessageCounter++;
    }

    @Override
    public void startResetMessageCount() {
        // Tworzenie i uruchamianie wątku odświerzającego
        refreshThread = new Thread(this::messegageCounterRefreshLoop);
        refreshThread.start();
    }

    @Override
    public void stopResetMessageCount() {
        refreshThread.interrupt();
    }

    private void messegageCounterRefreshLoop() {
        while (refreshThread.isAlive()) {
            try {
                Thread.sleep( 10000); // 10 sekund
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.receivedMessageCounter = 0;
        }
    }

    @Override
    public void run() {
            while (this.isWorking) {
                System.out.println("Debug: Jestem: " + this.getID() + "Mam: " + getReceivedMessageCount());
                RefreshEvent refreshEvent = new RefreshEvent(this, this);
                fireRefresh(refreshEvent);
            }
    }

    private void fireRefresh(RefreshEvent refreshEvent) {
        for (RefreshListner listener : listners) {
            listener.refresh(refreshEvent);
        }
    }

    public void addRefreshListner(RefreshListner listner) {
        this.listners.add(listner);
    }
    public void removeRefreshListner(RefreshListner listner) {
        this.listners.remove(listner);
    }


}
