package Graphics.Visualisations;

import InterfaceLink.StationLink;

import javax.swing.*;
import java.awt.*;

public class StationVisual extends JPanel {
    JLabel idLabel;
    JLabel processedMessageCounterLabel;
    JLabel waitingMessageCounterLabel;

    public StationVisual(StationLink stationLink) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));

        //Inicjalizacja komponentów
        idLabel = new JLabel("Id: " + stationLink.getId());
        processedMessageCounterLabel = new JLabel(
                "Processed: " + stationLink.getProcessedMessageCounter()
        );
        waitingMessageCounterLabel = new JLabel(
                "Waiting:" + stationLink.getWaitingMessageCounter()
        );


        //Id label
        idLabel.setPreferredSize(new Dimension(150,50));
        idLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        idLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        idLabel.setBackground(new Color(227, 244, 244));
        idLabel.setFont(new Font("Arial",Font.BOLD,20));


        //Dodanie komponentów do panelu
        add(idLabel);
        add(processedMessageCounterLabel);
        add(waitingMessageCounterLabel);



        setBackground(new Color(248, 246, 244));
    }

}
