package InterfaceLink;

import Graphics.Visualisations.StationVisual;
import Logic.Message;
import Logic.Station;

public interface VDBlink {
    int randomVRD();
    void sendMessage(int adress, Message message);
    Station findMostEmptyStation();

    void setMessage(Message message);
    void setFrequency(int frequency);
    void setIsWorking(boolean working);
    void setIsWaiting(boolean waiting);
    Message getMessage ();
    int getFrequency();
    int getID();
    boolean getIsWorking();
    boolean getIsWaiting();
    void run();
}
