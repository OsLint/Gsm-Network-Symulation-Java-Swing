package InterfaceLink;

import Graphics.Visualisations.LayerVisual;
import Logic.Message;
import Logic.Station;
import Logic.StationType;

/**
 * Interfejs StationLink.
 * Definiuje metody wspólne dla połączeia części graficznej z logiczną Stacji.
 */
public interface StationLink {
    int getId();
    int getProcessedMessageCounter();
    int getWaitingMessageCounter();
    StationType getType();
    Station findNextStation();
    LayerVisual findNextLayer();
    void reciveMessage(Message message);

    void setIsWorking(boolean bol);
    void createNewStation(Message message);

}
