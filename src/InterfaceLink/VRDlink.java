package InterfaceLink;

public interface VRDlink {
    void reciveMessage(String message);
    void startResetMessageCount();
    void stopResetMessageCount();
    int getID();
    int getReceivedMessageCount();
    void setIsWorking(boolean bool);
    boolean getIsWorking();
    boolean getResetMessageCount();


}
