package InterfaceLink;

public interface VRDlink {
    void startResetMessageCount();
    void stopResetMessageCount();

    int getID();
    int getReceivedMessageCount();
    void setIsWorking(boolean bool);
    void countMessage();
    boolean getIsWorking();

    boolean getResetMessageCount();


}
