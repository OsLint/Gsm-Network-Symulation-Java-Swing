package InterfaceLink;

import Logic.Message;

public interface VRDlink {
    void reciveMessage(Message message);
    void startResetMessageCount();
    void stopResetMessageCount();
    int getID();
    int getReceivedMessageCount();
    void setIsWorking(boolean bool);
    boolean getIsWorking();
    boolean getResetMessageCount();


}
