package InterfaceLink;

import Graphics.Visualisations.LayerVisual;
import Logic.Message;
import Logic.Station;
import Logic.StationType;

public interface StationLink {
    int getId();
    int getProcessedMessageCounter();
    int getWaitingMessageCounter();
    StationType getType();
    Station findNextStation();
    LayerVisual findNextLayer();
    void reciveMessage(Message message);
    Message getCurrentMessage();

    int getMaxMessageCap();

}
