package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.StationLink;
import Logic.Station;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class StationVisual extends JPanel implements RefreshListner {
    private final JLabel processedMessageCounterLabel;
    private final JLabel waitingMessageCounterLabel;


    public StationVisual(StationLink stationLink) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 100));


        JLabel idLabel = new JLabel("Id: " + stationLink.getId());
        processedMessageCounterLabel = new JLabel(
                "Processed: " + stationLink.getProcessedMessageCounter()
        );

        waitingMessageCounterLabel = new JLabel(
                "Waiting:" + stationLink.getWaitingMessageCounter()
        );


        waitingMessageCounterLabel.setForeground(new Color(50, 10, 255));


        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        idLabel.setPreferredSize(new Dimension(150, 50));
        idLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        idLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        idLabel.setBackground(Color.yellow);


        add(idLabel);
        add(processedMessageCounterLabel);
        add(waitingMessageCounterLabel);


        setBackground(new Color(248, 246, 244));
    }


    @Override
    public void refresh(RefreshEvent evt) {
        Station station = evt.getStation();
        processedMessageCounterLabel.setText("Processed: " + station.getProcessedMessageCounter());
        waitingMessageCounterLabel.setText("Waiting: " + station.getWaitingMessageCounter());
    }
}
