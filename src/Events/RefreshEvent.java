package Events;

import Logic.Station;
import Logic.VBD;
import Logic.VRD;

import java.util.EventObject;


public class RefreshEvent extends EventObject {
    private Station station;
    private VRD vrd;
    private VBD vbd;


    public RefreshEvent(Object source, Station station) {
        super(source);
        this.station = station;
    }


    public RefreshEvent(Object source, VRD vrd) {
        super(source);
        this.vrd = vrd;
    }


    public RefreshEvent(Object source, VBD vbd) {
        super(source);
        this.vbd = vbd;
    }


    public Station getStation() {
        return station;
    }


    public VRD getVrd() {
        return vrd;
    }


    public VBD getVbd() {
        return vbd;
    }
}
