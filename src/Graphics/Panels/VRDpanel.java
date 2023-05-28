package Graphics.Panels;


import Graphics.Visualisations.VRDvisual;
import Logic.VRD;

import java.awt.event.ActionEvent;

import static Graphics.Window.VRDlist;

/**
 * Klasa VRDpanel.
 * Panel dla Virtual Receiving Device (VRD).
 */
public class VRDpanel extends PanelTemplate {
    /**
     * Konstruktor klasy VRDpanel.
     * Inicjalizuje panel dla VRD z podanym tytułem.
     *
     * @param title Tytuł panelu.
     */
    public VRDpanel(String title) {
        super(title);
    }

    /**
     * Obsługuje akcję naciśnięcia przycisku "Add".
     * Tworzy nowy VRD, dodaje go do listy VRD oraz do panelu.
     * Aktualizuje widok aplikacji po wykonaniu operacji.
     *
     * @param e Obiekt reprezentujący zdarzenie akcji.
     */
    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        // Utworzenie nowego urządzenia odbiorczego VRD
        VRD newVRD = new VRD();
        VRDvisual newVRDvisual = new VRDvisual(newVRD);
        VRDlist.add(newVRD);
        newVRD.addRefreshListner(newVRDvisual);

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVRDvisual);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }


}
