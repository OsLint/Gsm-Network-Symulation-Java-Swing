package Graphics.Visualisations;

import Logic.Station;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LayerVisual extends JPanel {
    private JScrollPane deviceScrollPane;
    public ArrayList<Station> stationList;
    //private StationVisual baseStation;
    private JLabel titleLabel;
    private String title;
    private JPanel layerViewPort;
    private boolean end;
    public LayerVisual(String title) {

        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width)/9,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());


        //Inicjalizacja komponentów
        stationList = new ArrayList<>();
        Station baseStation = new Station();
        StationVisual baseStationVisual = new StationVisual(baseStation);
        stationList.add(baseStation);
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
        layerViewPort.add(baseStationVisual,BorderLayout.NORTH);

    }

    public String getTitle() {
        return title;
    }

    void addStation(){
        Station sv = new Station();
        stationList.add(sv);
        StationVisual stationVisual = new StationVisual(sv);
        layerViewPort.add(stationVisual);
    }

}
