package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Graphics.Window;
import InterfaceLink.VRDlink;

import java.util.ArrayList;


public class VRD implements VRDlink, Runnable {
    private final int Id;
    private int receivedMessageCounter;
    private boolean isWorking;
    private Thread refreshThread;
    private final ArrayList<RefreshListner> listners = new ArrayList<>();
    private boolean resetMesagesCounter;


    public VRD() {

        this.isWorking = true;
        this.receivedMessageCounter = 0;
        Window.phoneNumbers ++;
        this.Id = Window.phoneNumbers;
        resetMesagesCounter = false;


        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        while (this.isWorking) {
            RefreshEvent refreshEvent = new RefreshEvent(this, this);
            fireRefresh(refreshEvent);
        }
    }



    @Override
    public void reciveMessage(Message message) {
        receivedMessageCounter++;
        RefreshEvent refreshEvent = new RefreshEvent(this, this);
        fireRefresh(refreshEvent);
    }


    @Override
    public void startResetMessageCount() {

        resetMesagesCounter = true;
        refreshThread = new Thread(this::messegageCounterRefreshLoop);
        refreshThread.start();
    }


    @Override
    public void stopResetMessageCount() {
        resetMesagesCounter = false;
    }


    private void messegageCounterRefreshLoop() {
        while (refreshThread.isAlive()) {
            try {
                synchronized (this) {
                    wait(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (resetMesagesCounter) {
                this.receivedMessageCounter = 0;
            }
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


    @Override
    public int getID() {
        return Id;
    }



    @Override
    public boolean getIsWorking() {
        return isWorking;
    }


    @Override
    public int getReceivedMessageCount() {
        return receivedMessageCounter;
    }



    @Override
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }


}
