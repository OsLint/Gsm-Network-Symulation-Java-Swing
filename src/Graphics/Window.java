package Graphics;

import javax.swing.*;
import java.awt.*;

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

    JPanel VDBpanel = new VDBpanel();
    JPanel VRDpanel = new VRDpanel();



    this.getContentPane().add(VDBpanel,BorderLayout.WEST);
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
