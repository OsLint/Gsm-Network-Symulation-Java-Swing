package Logic;

import InterfaceLink.StationLink;

//2do:

public class Station implements StationLink {
    private static int counterId;
    private int Id;
    private int processedMessageCounter;
    private int waitingMessageCounter;
    private String title;

    public Station() {
        title = "test";
        counterId++;
        Id=counterId;
        processedMessageCounter = 0;
        waitingMessageCounter = 0;
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
}
