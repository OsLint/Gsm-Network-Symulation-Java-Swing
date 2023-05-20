package Logic;

import InterfaceLink.VRDlink;

import java.io.IOException;

public class VRD implements VRDlink {
    //Pola Prywatne
    private static int countId;
    private int Id;
    private int receivedMessageCounter;
    private boolean isWorking;


    //Konstruktor
    public VRD () {
        countId++;
        this.isWorking = true;
        this.receivedMessageCounter = 0;
        this.Id = countId;

        ///
        //receivedMessageCounter = 10000;

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
    public void countMessage() {
        receivedMessageCounter++;
    }
    @Override
    public void startResetMessageCount() {

         // resetThread.start();

    }

    @Override
    public void stopResetMessageCount() {
        //resetThread.interrupt();
    }
}
