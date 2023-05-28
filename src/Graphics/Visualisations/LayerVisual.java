package Graphics.Visualisations;

import Logic.Station;
import Logic.StationType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Klasa LayerVisual.
 * Panel reprezentujący warstwę wizualizacji.
 */
public class LayerVisual extends JPanel {
    public ArrayList<Station> stationList;
    private StationType type;
    private final String title;
    public JPanel layerViewPort;

    /**
     * Konstruktor klasy LayerVisual.
     * Inicjalizuje panel warstwy wizualizacji z podanym tytułem.
     *
     * @param title Tytuł warstwy.
     */
    public LayerVisual(String title) {

        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width) / 9,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());
        this.setType();


        //Inicjalizacja komponentów
        stationList = new ArrayList<>();
        Station baseStation = new Station(type);
        StationVisual baseStationVisual = new StationVisual(baseStation);

        stationList.add(baseStation);
        JLabel titleLabel = new JLabel(title);
        JScrollPane deviceScrollPane = new JScrollPane();
        layerViewPort = new JPanel();



        //Dodaj RefreshListner
        baseStation.addRefreshListener(baseStationVisual);

        //layerViewPort
        layerViewPort.setLayout(new BoxLayout(layerViewPort, BoxLayout.Y_AXIS));
        deviceScrollPane.setViewportView(layerViewPort);

        //TitleLabel
        titleLabel.setPreferredSize(new Dimension(150, 50));
        titleLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        titleLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titleLabel.setBackground(new Color(227, 244, 244));
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        //Device ScrollPane
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Tworzenie i ustawianie ramki
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        add(deviceScrollPane, BorderLayout.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        layerViewPort.add(baseStationVisual, BorderLayout.NORTH);

        this.setBackground(new Color(227, 244, 244));

    }

    /**
     * Wyłącza wszystkie stacje w warstwie.
     */
    public void turnOff() {
        for (Station station : stationList) {
            station.turnOff();
        }
    }

    /**
     * Sprawdza, czy warstwa zawiera daną stację.
     *
     * @param station Stacja do sprawdzenia.
     * @return True, jeśli warstwa zawiera stację. False w przeciwnym przypadku.
     */
    public boolean containsStation(Station station) {
        for (Station value : stationList) {
            if (station.getId() == value.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pobiera tytuł warstwy.
     *
     * @return Tytuł warstwy.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ustawia typ warstwy na podstawie tytułu.
     */
    public void setType() {
        if (this.getTitle().equalsIgnoreCase("BSC")) {
            this.type = StationType.BSC;
        } else {
            this.type = StationType.BTS;
        }
    }


}
