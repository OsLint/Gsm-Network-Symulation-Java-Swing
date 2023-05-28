package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.StationLink;
import Logic.Station;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa StationVisual.
 * Panel reprezentujący wizualizację stacji.
 */
public class StationVisual extends JPanel implements RefreshListner {
    private final JLabel processedMessageCounterLabel;
    private final JLabel waitingMessageCounterLabel;

    /**
     * Konstruktor klasy StationVisual.
     * Inicjalizuje panel wizualizacji stacji na podstawie obiektu StationLink.
     *
     * @param stationLink Obiekt StationLink reprezentujący stację.
     */
    public StationVisual(StationLink stationLink) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));

        //Inicjalizacja komponentów
        JLabel idLabel = new JLabel("Id: " + stationLink.getId());
        processedMessageCounterLabel = new JLabel(
                "Processed: " + stationLink.getProcessedMessageCounter()
        );
        waitingMessageCounterLabel = new JLabel(
                "Waiting:" + stationLink.getWaitingMessageCounter()
        );

        //Id label
        idLabel.setPreferredSize(new Dimension(150, 50));
        idLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        idLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        idLabel.setBackground(new Color(227, 244, 244));
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));


        //Dodanie komponentów do panelu
        add(idLabel);
        add(processedMessageCounterLabel);
        add(waitingMessageCounterLabel);


        setBackground(new Color(248, 246, 244));
    }

    /**
     * Metoda refresh.
     * Aktualizuje wyświetlane wartości liczników wiadomości na podstawie zdarzenia RefreshEvent.
     *
     * @param evt Zdarzenie RefreshEvent.
     */
    @Override
    public void refresh(RefreshEvent evt) {
        Station station = evt.getStation();
        processedMessageCounterLabel.setText("Processed: " + station.getProcessedMessageCounter());
        waitingMessageCounterLabel.setText("Waiting: " + station.getWaitingMessageCounter());
    }
}
