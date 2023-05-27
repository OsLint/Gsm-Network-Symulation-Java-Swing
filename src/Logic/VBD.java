package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Exeptions.NumberNotFoundExeption;
import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.VRDvisual;
import Graphics.Window;
import InterfaceLink.VBDlink;
import InterfaceLink.VRDlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class VBD implements VBDlink ,Runnable {

    //Pola prywatne
    private static int countId;
    private Message message;
    private int frequency;
    private boolean isWorking;
    private boolean isWaiting;
    private int Id;
    private Thread thread;
    private ArrayList<RefreshListner> listners = new ArrayList<>();

    //Konstruktor
    public VBD(Message message) {
        countId++;
        this.message = message;
        this.frequency = 1;
        this.isWorking = true;
        this.isWaiting = false;
        this.Id = countId;

        // Tworzenie i uruchamianie wątku
        thread = new Thread(this);
        thread.start();
    }


    //Settery
    @Override
    public void setMessage(Message message) {
        this.message= message;
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

    //Gettery
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
    @Override
    public boolean getIsWorking() {
        return isWorking;
    }
    @Override
    public boolean getIsWaiting() {
        return isWaiting;
    }
    @Override
    public int randomVRD() {
        if (Window.VRDlist == null || Window.VRDlist.isEmpty()) {
            try {
                throw new NumberNotFoundExeption(0);
            } catch (NumberNotFoundExeption nnfe) {
                //2 do posprzątaj i zastanów się czy chcesz wyświetlać komunikat
                System.out.println("Debug: " + "Nie istnieje dostępne " +
                        "urządzenie VRD. \n " +
                        "VDB: " + this.getID() + " oczekuje na dostępny VRD.");
            }
        }else {
            System.out.println(Window.VRDlist.size());
            Random random = new Random();
            int randomIndex = random.nextInt(Window.VRDlist.size());
            VRD tempVRD = Window.VRDlist.get(randomIndex);

            return tempVRD.getID();
        }
        return 0; //nie zaistnieje VRD o takim ID
    }

    @Override
    public void sendMessage( Message message) {
        //2DO
        Station nextStation = findMostEmptyStation();
        nextStation.reciveMessage(message);

    }

    @Override
    public Station findMostEmptyStation() {
        LayerVisual lv = Window.layers.get(0);
        ArrayList<Station> accessibleStations = lv.stationList;
        Station mostEmptyStation;
        if (!accessibleStations.isEmpty()) {
            mostEmptyStation = Collections.min(accessibleStations);
        }else {
            return null;
        }
        return mostEmptyStation;
    }
    //Medota odpowiedzialna za wątek
    @Override
    public void run() {
        while (isWorking) {
            if(isWaiting){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                int VRDnumber = randomVRD();
                if(VRDnumber != 0) {
                    message.setAdress(VRDnumber);
                    sendMessage(this.getMessage());
                }else {
                    fireRefresh(new RefreshEvent(this,this));
                }
            }
            try {
                Thread.sleep(1000 / frequency);
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
    public void removeRefreshListner(RefreshListner listner) {
        this.listners.remove(listner);
    }
}
