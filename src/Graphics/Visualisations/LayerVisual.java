package Graphics.Visualisations;

import Logic.Station;
import javax.swing.*;
import java.awt.*;

public class LayerVisual extends JPanel {
    private JScrollPane deviceScrollPane;
    private StationVisual baseStation;
    private JLabel titleLabel;
    private String title;
    private JPanel layerViewPort;
    public LayerVisual(String title) {

        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width)/9,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());


        //Inicjalizacja komponentów
        baseStation = new StationVisual(new Station());
        titleLabel = new JLabel(title);
        deviceScrollPane = new JScrollPane();
        layerViewPort = new JPanel();

        //layerViewPort
        layerViewPort.setLayout(new BoxLayout(layerViewPort,BoxLayout.Y_AXIS));
        deviceScrollPane.setViewportView(layerViewPort);


        //TitleLabel
        titleLabel.setPreferredSize(new Dimension(150,50));
        titleLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        titleLabel.setBackground(new Color(227, 244, 244));
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));

        //Device ScrollPane
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        add(deviceScrollPane,BorderLayout.CENTER);
        add(titleLabel,BorderLayout.NORTH);
        layerViewPort.add(baseStation,BorderLayout.NORTH);

    }

    public String getTitle() {
        return title;
    }
}
