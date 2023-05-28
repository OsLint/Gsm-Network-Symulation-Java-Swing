package Graphics.Panels;

import Graphics.Visualisations.VBDvisual;
import Logic.Message;
import Logic.VBD;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Graphics.Window.VBDlist;

/**
 * Klasa VBDpanel.
 * Panel dla Virtual Brodcast Device (VBD).
 */
public class VBDpanel extends PanelTemplate {
    /**
     * Konstruktor klasy VBDpanel.
     * Inicjalizuje panel dla VBD z podanym tytułem.
     *
     * @param title Tytuł panelu.
     */
    public VBDpanel(String title) {
        super(title);

    }

    /**
     * Obsługuje akcję naciśnięcia przycisku "Add".
     * Tworzy nowy VBD na podstawie wprowadzonej wiadomości, dodaje go do listy VBD oraz do panelu.
     * Aktualizuje widok aplikacji po wykonaniu operacji.
     *
     * @param e Obiekt reprezentujący zdarzenie akcji.
     */
    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

        VBD newVBD = new VBD(new Message(message));
        VBDvisual newVBDvisual = new VBDvisual(newVBD);
        //Refresh listner
        newVBD.addRefreshListner(newVBDvisual);

        // Dodanie nowego urządzenia do listy VBD
        VBDlist.add(newVBD);

        // Dodanie nowego urządzenia do panelu z urządzeniami
        viewportView.add(newVBDvisual);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }


}
