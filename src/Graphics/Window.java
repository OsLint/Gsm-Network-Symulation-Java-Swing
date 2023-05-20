package Graphics;

import Graphics.Panels.LayerPanel;
import Graphics.Panels.VDBpanel;
import Graphics.Panels.VRDpanel;
import Logic.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Window extends JFrame {

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
            }
        });



    VDBpanel VDBpanel = new VDBpanel("VDB Panel");
    LayerPanel layerPanel = new LayerPanel("Layer Panel");
    VRDpanel VRDpanel = new VRDpanel("VRD Panel");



    this.getContentPane().add(VDBpanel,BorderLayout.WEST);
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
}
