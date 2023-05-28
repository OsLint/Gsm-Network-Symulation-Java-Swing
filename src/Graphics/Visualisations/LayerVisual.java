package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import Logic.Station;
import Logic.StationType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LayerVisual extends JPanel {
    private JScrollPane deviceScrollPane;
    public ArrayList<Station> stationList;
    private StationType type;
    private JLabel titleLabel;
    private String title;
    public JPanel layerViewPort;

    public LayerVisual(String title) {

        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width)/9,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());
        this.setType();


        //Inicjalizacja komponentów
        stationList = new ArrayList<>();
        Station baseStation = new Station(type);
        StationVisual baseStationVisual = new StationVisual(baseStation);

        stationList.add(baseStation);
        titleLabel = new JLabel(title);
        deviceScrollPane = new JScrollPane();
        layerViewPort = new JPanel();

        //Dodaj RefreshListner
        baseStation.addRefreshListener(baseStationVisual);
       // baseStation.addRefreshListener(this);

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

    public void setType() {
        if(this.getTitle().toUpperCase().equals("BSC")) {
            this.type = StationType.BSC;
        }else {
            this.type = StationType.BTS;
        }
    }

    public void turnOff(){
        for (int i = 0; i < stationList.size(); i++) {
            stationList.get(i).setIsWorking(false);
        }
    }
    public boolean containsStation(Station station){
        for (int i = 0; i < stationList.size(); i++) {
            if(station.getId() == stationList.get(i).getId()){
                return true;
            }
        }
        return false;
    }

}
