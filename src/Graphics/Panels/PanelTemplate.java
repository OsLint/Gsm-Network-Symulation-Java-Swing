package Graphics.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelTemplate extends JPanel {
    protected JScrollPane deviceScrollPane;
    protected JButton addButton;
    protected JLabel titleTextField;
    protected JPanel viewportView;

    protected String title;


    public  PanelTemplate (String title) {
        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width)/3,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());

        //Inicjalizacja komponentów
        titleTextField = new JLabel(title);
        addButton = new JButton("Add");
        deviceScrollPane = new JScrollPane();
        viewportView = new JPanel();


        //ViewPort
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.Y_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddButtonAction(e);
            }
        });


        add(titleTextField,BorderLayout.NORTH);
        add(deviceScrollPane,BorderLayout.CENTER);
        add(addButton,BorderLayout.SOUTH);



        setBackground(new Color(248, 246, 244));


    }

    protected abstract void handleAddButtonAction(ActionEvent e);

}
