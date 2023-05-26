package Logic;

import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.StationVisual;
import InterfaceLink.StationLink;

import java.util.ArrayList;
import java.util.Random;

//2do:

public class Station implements StationLink,Runnable, Comparable<Station> {
    private static int counterId;
    private int Id;
    private int processedMessageCounter;
    private int waitingMessageCounter;
    private String title;
    private ArrayList<Message> messagesInDeckList;
    private Message currentMessage;
    private int maxMessagesCap;
    private Thread thread;

    public Station() {
        title = "test";
        maxMessagesCap = 5;
        counterId++;
        Id=counterId;
        processedMessageCounter = 0;
        messagesInDeckList = new ArrayList<Message>();
        waitingMessageCounter = messagesInDeckList.size();
        // Tworzenie i uruchamianie wątku
        thread = new Thread(this);
        thread.start();
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
    public String getTitle() {
        return title;
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
                ", title='" + title + '\'' +
                ", messagesInDeckList=" + messagesInDeckList +
                '}';
    }

    @Override
    public void run() {
        Random random = new Random();
        int sleepTime = random.nextInt(11) + 5; //Losowy Czas od 5 do 15 sekund
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
