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

public class Window extends JFrame {

    public static ArrayList<VBD> VBDlist = new ArrayList<>();
    public static ArrayList<VRD> VRDlist = new ArrayList<>();
    public static ArrayList<LayerVisual> layers = new ArrayList<>();
    public static int phoneNumbers = 504111111;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();


    public Window() {

        super("GSM SYMULATION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new FileHandler();
            }
        });


        VBDpanel VBDpanel = new VBDpanel("VBD PANEL");
        LayerPanel layerPanel = new LayerPanel("LAYER PANEL");
        VRDpanel VRDpanel = new VRDpanel("VRD PANEL");


        this.getContentPane().add(VBDpanel, BorderLayout.WEST);
        this.getContentPane().add(layerPanel, BorderLayout.CENTER);
        this.getContentPane().add(VRDpanel, BorderLayout.EAST);
    }


    @Override
    public int getWidth() {
        return width;
    }


    @Override
    public int getHeight() {
        return height;
    }


    public static void showInfoDialog(String title, String message, ImageIcon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
