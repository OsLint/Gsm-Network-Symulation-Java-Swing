package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Exeptions.NumberNotFoundExeption;

import Graphics.Window;
import InterfaceLink.VBDlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class VBD implements VBDlink, Runnable {
    private Message message;
    private int frequency;
    private boolean isWorking;
    private boolean isWaiting;
    private final int Id;
    private int messageSendedCounter;
    private final ArrayList<RefreshListner> listners = new ArrayList<>();


    public VBD(Message message) {

        Window.phoneNumbers++;
        this.message = message;
        this.frequency = 1;
        this.isWorking = true;
        this.isWaiting = false;
        this.Id = Window.phoneNumbers;


        Thread thread = new Thread(this);
        thread.start();
    }




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
        return 0;
    }

    @Override
    public void sendMessage(Message message) {
        Station nextStation = findMostEmptyStation();
        nextStation.reciveMessage(message);
        messageSendedCounter++;

    }


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


    private void fireRefresh(RefreshEvent refreshEvent) {
        for (RefreshListner listener : listners) {
            listener.refresh(refreshEvent);
        }
    }


    public void addRefreshListner(RefreshListner listner) {
        this.listners.add(listner);
    }


    @Override
    public void setMessage(Message message) {
        this.message = message;
    }


    @Override
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    @Override
    public void setIsWorking(boolean working) {
        this.isWorking = working;
    }


    @Override
    public void setIsWaiting(boolean waiting) {
        this.isWaiting = waiting;
    }


    @Override
    public Message getMessage() {
        return message;
    }


    @Override
    public int getFrequency() {
        return frequency;
    }


    @Override
    public int getID() {
        return Id;
    }


    public int getMessageSendedCounter() {
        return messageSendedCounter;
    }
}
