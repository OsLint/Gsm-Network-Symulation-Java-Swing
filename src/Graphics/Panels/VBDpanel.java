package Graphics.Panels;

import Graphics.Visualisations.VBDvisual;
import Logic.Message;
import Logic.VBD;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Graphics.Window.VBDlist;


public class VBDpanel extends PanelTemplate {

    public VBDpanel(String title) {
        super(title);

    }


    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

        VBD newVBD = new VBD(new Message(message));
        VBDvisual newVBDvisual = new VBDvisual(newVBD);

        newVBD.addRefreshListner(newVBDvisual);


        VBDlist.add(newVBD);


        viewportView.add(newVBDvisual);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }


}
