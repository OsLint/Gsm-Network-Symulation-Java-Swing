package InterfaceLink;

public interface VRDlink {
    void reciveMessage(String message);
    public void processMessages();
    void startResetMessageCount();
    void stopResetMessageCount();
    int getID();
    int getReceivedMessageCount();
    void setIsWorking(boolean bool);
    void countMessage();
    boolean getIsWorking();
    boolean getResetMessageCount();


}
