package InterfaceLink;

import Graphics.Visualisations.LayerVisual;
import Logic.Message;
import Logic.Station;

public interface StationLink {
    int getId();
    int getProcessedMessageCounter();
    int getWaitingMessageCounter();
    String getTitle();
    Station findNextStation();
    LayerVisual findNextLayer();
    void reciveMessage(Message message);
    Message getCurrentMessage();

    int getMaxMessageCap();

}
