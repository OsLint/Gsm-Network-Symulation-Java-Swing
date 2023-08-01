package Graphics.Visualisations;

import Logic.Station;
import Logic.StationType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;


public class LayerVisual extends JPanel {
    public ArrayList<Station> stationList;
    private StationType type;
    private final String title;
    public JPanel layerViewPort;


    public LayerVisual(String title) {

        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width) / 9,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());
        this.setType();



        stationList = new ArrayList<>();
        Station baseStation = new Station(type);
        StationVisual baseStationVisual = new StationVisual(baseStation);

        stationList.add(baseStation);
        JLabel titleLabel = new JLabel(title);
        JScrollPane deviceScrollPane = new JScrollPane();
        layerViewPort = new JPanel();




        baseStation.addRefreshListener(baseStationVisual);


        layerViewPort.setLayout(new BoxLayout(layerViewPort, BoxLayout.Y_AXIS));
        deviceScrollPane.setViewportView(layerViewPort);


        titleLabel.setPreferredSize(new Dimension(150, 50));
        titleLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titleLabel.setBackground(new Color(227, 244, 244));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);


        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        add(deviceScrollPane, BorderLayout.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        layerViewPort.add(baseStationVisual, BorderLayout.NORTH);

        this.setBackground(new Color(227, 244, 244));

    }


    public void turnOff() {
        for (Station station : stationList) {
            station.turnOff();
        }
    }


    public boolean containsStation(Station station) {
        for (Station value : stationList) {
            if (station.getId() == value.getId()) {
                return true;
            }
        }
        return false;
    }


    public String getTitle() {
        return title;
    }


    public void setType() {
        if (this.getTitle().equalsIgnoreCase("BSC")) {
            this.type = StationType.BSC;
        } else {
            this.type = StationType.BTS;
        }
    }


}
