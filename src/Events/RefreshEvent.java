package Events;

import Logic.Station;
import Logic.VDB;
import Logic.VRD;

import java.util.EventObject;

public class RefreshEvent extends EventObject {
    private Station station;
    private VRD vrd;
    private VDB vdb;
    public RefreshEvent(Object source, Station station) {
        super(source);
        this.station = station;
    }

    public RefreshEvent(Object source, VRD vrd) {
        super(source);
        this.vrd = vrd;
    }

    public RefreshEvent(Object source, VDB vdb) {
        super(source);
        this.vdb = vdb;
    }

    public Station getStation() {
        return station;
    }

    public VRD getVrd() {
        return vrd;
    }

    public VDB getVdb() {
        return vdb;
    }
}
