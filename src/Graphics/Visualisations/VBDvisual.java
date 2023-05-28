package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.VBDlink;
import Logic.VBD;

import javax.swing.*;
import java.awt.*;

import static Graphics.Window.VBDlist;

public class VBDvisual extends JPanel implements RefreshListner {
    private JLabel messageLabel;
    private JSlider frequencySlider;
    private JButton stopButton;
    private JTextField idTextField;
    private JComboBox statusComboBox;
    public VBDvisual (VBDlink vbDlink) {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));


        //Inicjalizacja komponentów
        messageLabel = new JLabel("Message: " + vbDlink.getMessage());
        frequencySlider = new JSlider(JSlider.HORIZONTAL,1,5,vbDlink.getFrequency());
        stopButton = new JButton("Stop");
        idTextField = new JTextField("ID: " + vbDlink.getID());
        statusComboBox = new JComboBox(new String[] {"ACTIVE", "WAITING"});

        //Id text field
        idTextField.setPreferredSize(new Dimension(150,50));
        idTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        idTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        idTextField.setBackground(new Color(227, 244, 244));
        idTextField.setFont(new Font("Arial",Font.BOLD,20));
        idTextField.setEditable(false);

        //Frequency slider
        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        frequencySlider.setSnapToTicks(true);
        frequencySlider.addChangeListener(e -> {
            vbDlink.setFrequency(frequencySlider.getValue());
        });

        //Stop button
        stopButton.addActionListener(e -> {
            vbDlink.setIsWorking(false);
            setVisible(false);
            for (int i = 0; i < VBDlist.size(); i++) {
                if(VBDlist.get(i) == vbDlink){
                    VBDlist.remove(vbDlink);
                }
            }
        });

        //statusComboBox
        statusComboBox.addActionListener(e -> {
            String selectedStatus = (String) statusComboBox.getSelectedItem();
            if (selectedStatus != null) {
                if (selectedStatus.equals("ACTIVE")){
                    vbDlink.setIsWaiting(false);
                    vbDlink.setIsWorking(true);
                    System.out.println(vbDlink);
                } else if (selectedStatus.equals("WAITING")) {
                    vbDlink.setIsWaiting(true);
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
    @Override
    public void refresh(RefreshEvent evt) {
        VBD vbd= evt.getVbd();
        statusComboBox.setSelectedItem("WAITING");
    }
}
