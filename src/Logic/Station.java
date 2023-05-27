package Logic;

import Events.RefreshEvent;
import Events.RefreshListner;
import Graphics.Visualisations.LayerVisual;
import InterfaceLink.StationLink;

import java.util.ArrayList;
import java.util.Random;

//2do:

public class Station implements StationLink,Runnable, Comparable<Station>, RefreshListner {

    private static int counterId;
    private int Id;
    private int processedMessageCounter;
    private int waitingMessageCounter;
    private StationType type;
    private ArrayList<Message> messagesInDeckList; //Oczekujące Wiadomości
    private Message currentMessage;
    private int maxMessagesCap;
    private Thread thread; //Główny wątek
    private Thread refreshThread; //Poboczny wątek służący do odświerzania
    private ArrayList<RefreshListner> listeners = new ArrayList<>();

    public Station(StationType type) {
        this.type = type;
        maxMessagesCap = 5;
        counterId++;
        Id=counterId;
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
    public int getMaxMessageCap() {
        return maxMessagesCap;
    }

    @Override
    public Station findNextStation() {
        //2DO;
        return null;
    }
    @Override
    public LayerVisual findNextLayer() {
        //2DO
        return null;
    }
    @Override
    public void reciveMessage(Message message) {
        if(messagesInDeckList.size() < 5) {
            messagesInDeckList.add(message);
            waitingMessageCounter = messagesInDeckList.size();
        if(messagesInDeckList.size() == 5) {
            //createNewStation();
        }
        }else {
            //2DO:
            //creteNewStation();
            //Station newStation = new Station();
            //newStation.reciveMessage(message);
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
                ", title='" + type + '\'' +
                ", messagesInDeckList=" + messagesInDeckList +
                '}';
    }

    public void addRefreshListener(RefreshListner listener) {
        listeners.add(listener);
    }

    public void removeRefreshListener(RefreshListner listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        while(thread.isAlive()) {
            //System.out.println("Debug: Witam jestem stacja : " + this.getId()
            //        + " mam: " + this.getWaitingMessageCounter() + " Wiadomości:");
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
                processedMessageCounter++;
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
    @Override
    public void refresh(RefreshEvent evt) {
        //nie trzeba implementować
    }

    
}
