package Graphics.Visualisations;

import InterfaceLink.VRDlink;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VRDvisual extends JPanel {
    JTextField idTextField;
    JButton stopButton;
    JLabel messageCounter;
    JCheckBox clearTimeCheckBox;

    public VRDvisual (VRDlink vrDlink) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));

        //Inicjalizacja komponentów
        idTextField = new JTextField("ID: " + vrDlink.getID());
        stopButton = new JButton("Stop");
        messageCounter = new JLabel(
                "Message count: " +
                vrDlink.getReceivedMessageCount()
        );
        clearTimeCheckBox = new JCheckBox("Reset Counter");

        //ClearTimeCheckBox
        clearTimeCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        clearTimeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clearTimeCheckBox.isSelected()) {
                    vrDlink.startResetMessageCount();
                }else {
                    vrDlink.stopResetMessageCount();

                }
            }
        });

        //MessageCounter
        messageCounter.setAlignmentX(Component.LEFT_ALIGNMENT);


        //Id text field
        idTextField.setPreferredSize(new Dimension(150,50));
        idTextField.setBackground(new Color(227, 244, 244));
        idTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        idTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        idTextField.setFont(new Font("Arial",Font.BOLD,20));
        idTextField.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Stop button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vrDlink.setIsWorking(false);
                setVisible(false);
            }
        });
        stopButton.setAlignmentX(Component.LEFT_ALIGNMENT);




        //Dodanie komponentów do panelu
        add(idTextField);
        add(stopButton);
        add(messageCounter);
        add(clearTimeCheckBox);

        setBackground(new Color(248, 246, 244));


    }


}
