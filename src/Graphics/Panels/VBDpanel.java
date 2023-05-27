package Graphics.Panels;

import Events.RefreshEvent;
import Exeptions.NumberNotFoundExeption;
import Graphics.Visualisations.VBDvisual;
import Graphics.Window;
import Logic.Message;
import Logic.VBD;
import Logic.VRD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import static Graphics.Window.VBDlist;

public class VBDpanel extends PanelTemplate {
    public VBDpanel(String title) {
        super(title);
    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

        // Utworzenie nowego urządzenia nadawczego na podstawie wprowadzonej wiadomości


        //Tymczasowa metoda:


        //int randomVRD = randomVRD();
        VBD newVBD = new VBD(new Message(message));
        VBDvisual newVBDvisual = new VBDvisual(newVBD);
        //Refresh listner
        newVBD.addRefreshListner(newVBDvisual);

        // Dodanie nowego urządzenia do listy
        VBDlist.add(newVBD);

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVBDvisual);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }

   /* //tymczasowa metoda
    public int randomVRD() {
        if (Window.VRDlist == null || Window.VRDlist.isEmpty()) {
            System.out.println("Nie ma dostępnego vrd");
            return 0; //nie zaistnieje VRD o takim ID
        }else {
            System.out.println(Window.VRDlist.size());
            Random random = new Random();
            int randomIndex = random.nextInt(Window.VRDlist.size());
            VRD tempVRD = Window.VRDlist.get(randomIndex);

            return tempVRD.getID();
        }
    }*/

}
