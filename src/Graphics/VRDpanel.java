package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VRDpanel extends JPanel {

    private JScrollPane deviceScrollPane;
    private JButton addButton;



    public VRDpanel(int width,int height) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));


        deviceScrollPane = new JScrollPane();
        addButton = new JButton("Add");


        add(deviceScrollPane,BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);


    }
}
