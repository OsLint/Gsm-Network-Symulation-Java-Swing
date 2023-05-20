package Logic;

import InterfaceLink.VDBlink;

public class VDB implements VDBlink {

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
    /*private void startWorkingThread() {
        Thread workingThread = new Thread(() -> {
            while (isWorking) {
                // Symulacja pracy urządzenia
                try {
                    Thread.sleep(frequency * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Zmiana statusu urządzenia na "WAITING"
                isWaiting = true;
                statusComboBox.setSelectedItem("WAITING");

                // Symulacja oczekiwania na odpowiedź
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Zmiana statusu urządzenia na "ACTIVE"
                isWaiting = false;
                statusComboBox.setSelectedItem("ACTIVE");
            }
        });

        workingThread.start();
    }*/
}
