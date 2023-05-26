package Graphics.Visualisations;

import InterfaceLink.StationLink;

import javax.swing.*;
import java.awt.*;

public class StationVisual extends JPanel {
    private JLabel idLabel;
    private JLabel processedMessageCounterLabel;
    private JLabel waitingMessageCounterLabel;
    private int messagesInDeck;

    public StationVisual(StationLink stationLink) {
        this.messagesInDeck = stationLink.getWaitingMessageCounter();
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



    @Override
    public String toString() {
        return "StationVisual{" +
                "idLabel=" + idLabel +
                ", processedMessageCounterLabel=" + processedMessageCounterLabel +
                ", waitingMessageCounterLabel=" + waitingMessageCounterLabel +
                ", messagesInDeck=" + messagesInDeck +
                '}';
    }
}
