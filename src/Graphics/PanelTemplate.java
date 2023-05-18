package Graphics;

import Logic.VDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelTemplate extends JPanel {
    protected JScrollPane deviceScrollPane;
    protected JButton addButton;


    public PanelTemplate () {
        setPreferredSize(new Dimension(1920/3, 1080));
        setLayout(new BorderLayout());

        //Inicjalizacja komponentów
        addButton = new JButton("Add");
        deviceScrollPane = new JScrollPane();
        JPanel viewportView = new JPanel();

        //ViewPort
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.Y_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        add(deviceScrollPane,BorderLayout.CENTER);
        add(addButton,BorderLayout.SOUTH);



        setBackground(new Color(248, 246, 244));


    }


}
