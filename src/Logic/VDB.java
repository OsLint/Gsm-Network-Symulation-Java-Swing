package Logic;

import InterfaceLink.VDBlink;

import javax.swing.*;
import java.util.Random;

public class VDB implements VDBlink ,Runnable {

    //Pola prywatne
    private static int countId;
    private String message;
    private int frequency;
    private boolean isWorking;
    private boolean isWaiting;
    private int Id;

    //Konstruktor
    public VDB( String message) {
        countId++;
        this.message = message;
        this.frequency = 1;
        this.isWorking = true;
        this.isWaiting = false;
        this.Id = countId;
    }
    //Settery
    @Override
    public void setMessage(String message) {
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
    public String getMessage() {
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

    //Medota odpowiedzialna za wątek
    @Override
    public void run() {
        while (isWorking) {
            //Sprawdzamy status urządzenia
            if(isWaiting) {
                try {
                    Thread.sleep(100);
                    System.out.println("test0");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                //Wygenerowanie losowego numeru telefonu
                //2DO: numer ma być generowany spośród numerów istniejących bts
                int btsNumber = new Random().nextInt();

            }
            try {
                Thread.sleep(1000 / frequency);
                System.out.println("test1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
