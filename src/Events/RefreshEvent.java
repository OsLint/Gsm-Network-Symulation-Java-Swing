package Events;

import Logic.Station;
import Logic.VBD;
import Logic.VRD;

import java.util.EventObject;

/**
 * Klasa RefreshEvent.
 * Reprezentuje zdarzenie odświeżenia, które zawiera informacje o obiekcie Stacji, VRD lub VBD.
 */
public class RefreshEvent extends EventObject {
    private Station station;
    private VRD vrd;
    private VBD vbd;

    /**
     * Konstruktor klasy RefreshEvent dla stacji.
     *
     * @param source  Obiekt źródłowy związany z zdarzeniem.
     * @param station Stacja, która wymaga odświeżenia.
     */
    public RefreshEvent(Object source, Station station) {
        super(source);
        this.station = station;
    }

    /**
     * Konstruktor klasy RefreshEvent dla VRD.
     *
     * @param source Obiekt źródłowy związany z zdarzeniem.
     * @param vrd    VRD, który wymaga odświeżenia.
     */
    public RefreshEvent(Object source, VRD vrd) {
        super(source);
        this.vrd = vrd;
    }

    /**
     * Konstruktor klasy RefreshEvent dla VBD.
     *
     * @param source Obiekt źródłowy związany z zdarzeniem.
     * @param vbd    VBD, który wymaga odświeżenia.
     */
    public RefreshEvent(Object source, VBD vbd) {
        super(source);
        this.vbd = vbd;
    }

    /**
     * Metoda zwraca stację związana z zdarzeniem odświeżenia.
     *
     * @return Stacja wymagająca odświeżenia.
     */
    public Station getStation() {
        return station;
    }

    /**
     * Metoda zwraca VRD związany z zdarzeniem odświeżenia.
     *
     * @return VRD wymagający odświeżenia.
     */
    public VRD getVrd() {
        return vrd;
    }

    /**
     * Metoda zwraca VBD związany z zdarzeniem odświeżenia.
     *
     * @return VBD wymagający odświeżenia.
     */
    public VBD getVbd() {
        return vbd;
    }
}
