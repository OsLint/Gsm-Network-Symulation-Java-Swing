package Graphics;

import Graphics.Panels.LayerPanel;
import Graphics.Panels.VBDpanel;
import Graphics.Panels.VRDpanel;
import Graphics.Visualisations.LayerVisual;
import Logic.FileHandler;
import Logic.VBD;
import Logic.VRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

/**
 * Klasa Window.
 * Główne okno aplikacji GUI.
 */
public class Window extends JFrame {

    public static ArrayList<VBD> VBDlist = new ArrayList<>();
    public static ArrayList<VRD> VRDlist = new ArrayList<>();
    public static ArrayList<LayerVisual> layers = new ArrayList<>();

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();

    /**
     * Konstruktor klasy Window.
     * Inicjalizuje główne okno aplikacji GUI.
     */
    public Window() {

        super("GSM SYMULATION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new FileHandler("test1");
            }
        });


        VBDpanel VBDpanel = new VBDpanel("VBD PANEL");
        LayerPanel layerPanel = new LayerPanel("LAYER PANEL");
        VRDpanel VRDpanel = new VRDpanel("VRD PANEL");


        this.getContentPane().add(VBDpanel, BorderLayout.WEST);
        this.getContentPane().add(layerPanel, BorderLayout.CENTER);
        this.getContentPane().add(VRDpanel, BorderLayout.EAST);
    }

    /**
     * Metoda getWidth.
     * Zwraca szerokość okna.
     *
     * @return Szerokość okna.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Metoda getHeight.
     * Zwraca wysokość okna.
     *
     * @return Wysokość okna.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Metoda showInfoDialog.
     * Wyświetla okno dialogowe z informacją.
     *
     * @param title   Tytuł okna dialogowego.
     * @param message Treść informacji.
     * @param icon    Ikona okna dialogowego.
     */
    public static void showInfoDialog(String title, String message, ImageIcon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
