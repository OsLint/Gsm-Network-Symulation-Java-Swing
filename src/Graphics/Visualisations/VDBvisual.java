package Graphics.Visualisations;

import InterfaceLink.VDBlink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VDBvisual extends JPanel {
    private JLabel messageLabel;
    private JSlider frequencySlider;
    private JButton stopButton;
    private JTextField idTextField;
    private JComboBox statusComboBox;
    public VDBvisual (VDBlink vdBlink) {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));


        //Inicjalizacja komponentów
        messageLabel = new JLabel("Message: " + vdBlink.getMessage());
        frequencySlider = new JSlider(JSlider.HORIZONTAL,1,5,vdBlink.getFrequency());
        stopButton = new JButton("Stop");
        idTextField = new JTextField("ID: " + vdBlink.getID());
        statusComboBox = new JComboBox(new String[] {"ACTIVE", "WAITING"});

        //Id text field
        idTextField.setPreferredSize(new Dimension(150,50));
        idTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        idTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        idTextField.setBackground(new Color(227, 244, 244));
        idTextField.setFont(new Font("Arial",Font.BOLD,20));

        //Frequency slider
        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        frequencySlider.setSnapToTicks(true);
        frequencySlider.addChangeListener(e -> {
            vdBlink.setFrequency(frequencySlider.getValue());
        });

        //Stop button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vdBlink.setIsWorking(false);
                setVisible(false);
            }
        });

        //statusComboBox
        statusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = (String) statusComboBox.getSelectedItem();
                if (selectedStatus != null) {
                    if (selectedStatus.equals("ACTIVE")){
                        vdBlink.setIsWaiting(false);
                        vdBlink.setIsWorking(true);

                        //System.out.println("Debug: " + vdBlink.getID() + " is Active");
                    } else if (selectedStatus.equals("WAITING")) {
                        vdBlink.setIsWaiting(true);
                        vdBlink.setIsWorking(false);

                        // System.out.println("Debug: "+ vdBlink.getID() + " is  Waiting");
                    }
                }

            }
        });

        //Dodanie komponentów do panelu
        add(idTextField);
        add(messageLabel);
        add(frequencySlider);
        add(stopButton);
        add(statusComboBox);

        // setPreferredSize(new Dimension(250, 150));
        setBackground(new Color(248, 246, 244));

    }
}
