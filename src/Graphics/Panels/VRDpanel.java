package Graphics.Panels;


import Graphics.Visualisations.VRDvisual;
import Logic.VRD;

import java.awt.event.ActionEvent;

import static Graphics.Window.VRDlist;

public class VRDpanel extends PanelTemplate{
    public VRDpanel(String title) {
        super(title);
    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        // Utworzenie nowego urządzenia odbiorczego
        VRDvisual newVRD = new VRDvisual(new VRD());
        VRDlist.add(newVRD);

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVRD);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }



}
