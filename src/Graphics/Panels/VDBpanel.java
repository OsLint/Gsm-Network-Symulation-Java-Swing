package Graphics.Panels;

import Graphics.Visualisations.VDBvisual;
import Logic.VDB;
import javax.swing.*;
import java.awt.event.ActionEvent;
public class VDBpanel extends PanelTemplate {

    public VDBpanel(String title) {
        super(title);
    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

        // Utworzenie nowego urządzenia nadawczego na podstawie wprowadzonej wiadomości
        VDBvisual newVDB = new VDBvisual(new VDB(message));

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVDB);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }


}
