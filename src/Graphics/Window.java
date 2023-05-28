package Graphics;

import Graphics.Panels.LayerPanel;
import Graphics.Panels.VBDpanel;
import Graphics.Panels.VRDpanel;
import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.VBDvisual;
import Graphics.Visualisations.VRDvisual;
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

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();

    public Window() {

    super("GuiSet03");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(width,height);
    setVisible(true);
    setLayout(new BorderLayout());

    addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileHandler fileHandler = new FileHandler("test1");


               /* Thread.getAllStackTraces().keySet().forEach(thread -> {
                    System.out.println("Thread name: " + thread.getName());
                    System.out.println("Thread ID: " + thread.getId());
                    System.out.println("Thread state: " + thread.getState());
                    System.out.println("----------------------------------------" + thread);
                });*/

            }
        });




    VBDpanel VBDpanel = new VBDpanel("VBD Panel");
    LayerPanel layerPanel = new LayerPanel("Layer Panel");
    VRDpanel VRDpanel = new VRDpanel("VRD Panel");



    this.getContentPane().add(VBDpanel,BorderLayout.WEST);
    this.getContentPane().add(layerPanel,BorderLayout.CENTER);
    this.getContentPane().add(VRDpanel,BorderLayout.EAST);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    //Metoda odpowiedzialna za wyświetlanie Info Dialogu
    public static void showInfoDialog(String title, String message,ImageIcon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
