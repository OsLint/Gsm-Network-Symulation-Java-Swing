package Graphics.Panels;

import Graphics.Visualisations.VDBvisual;
import Logic.VDB;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Graphics.Window.VDBlist;

public class VDBpanel extends PanelTemplate {
    //private List<VDBvisual> vdbList = new ArrayList<>(); // Lista przechowująca obiekty VDB

    public VDBpanel(String title) {
        super(title);
    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

        // Utworzenie nowego urządzenia nadawczego na podstawie wprowadzonej wiadomości
        VDBvisual newVDB = new VDBvisual(new VDB(message));

        // Dodanie nowego urządzenia do listy
        VDBlist.add(newVDB);

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVDB);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }


}
