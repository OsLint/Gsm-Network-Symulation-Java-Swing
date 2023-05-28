package InterfaceLink;

import Logic.Message;

/**
 * Interfejs VRDlink.
 * Definiuje metody dla połączeia części graficznej i logicznej VRD.
 */
public interface VRDlink {
    void reciveMessage(Message message);
    void startResetMessageCount();
    void stopResetMessageCount();
    int getID();
    int getReceivedMessageCount();
    void setIsWorking(boolean bool);
    boolean getIsWorking();


}
