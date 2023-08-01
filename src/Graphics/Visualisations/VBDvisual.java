package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.VBDlink;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static Graphics.Window.VBDlist;


public class VBDvisual extends JPanel implements RefreshListner {
    private final JSlider frequencySlider;
    private final JComboBox<String> statusComboBox;

    public VBDvisual (VBDlink vbDlink) {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));



        JLabel messageLabel = new JLabel("Message: " + vbDlink.getMessage());
        frequencySlider = new JSlider(JSlider.HORIZONTAL,1,5,vbDlink.getFrequency());
        JButton stopButton = new JButton("Stop");
        JTextField idTextField = new JTextField("Phone Number: +48" + vbDlink.getID());
        statusComboBox = new JComboBox<>(new String[]{"ACTIVE", "WAITING"});


        idTextField.setPreferredSize(new Dimension(150,50));
        idTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE,30));
        idTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        idTextField.setBackground(new Color(227, 244, 244));
        idTextField.setFont(new Font("Arial",Font.BOLD,20));
        idTextField.setEditable(false);


        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        frequencySlider.setSnapToTicks(true);
        frequencySlider.addChangeListener(e -> vbDlink.setFrequency(frequencySlider.getValue()));


        stopButton.setForeground(new Color(255, 0, 0));
        stopButton.addActionListener(e -> {
            vbDlink.setIsWorking(false);
            setVisible(false);
            for (int i = 0; i < VBDlist.size(); i++) {
                if(VBDlist.get(i) == vbDlink){
                    VBDlist.remove(vbDlink);
                }
            }
        });


        statusComboBox.addActionListener(e -> {
            String selectedStatus = (String) statusComboBox.getSelectedItem();
            if (selectedStatus != null) {
                if (selectedStatus.equals("ACTIVE")){
                    vbDlink.setIsWaiting(false);
                    vbDlink.setIsWorking(true);
                    synchronized (vbDlink) {
                        vbDlink.notifyAll();
                    }
                } else if (selectedStatus.equals("WAITING")) {
                    vbDlink.setIsWaiting(true);
                }
            }
        });


        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        add(idTextField);
        add(messageLabel);
        add(frequencySlider);
        add(stopButton);
        add(statusComboBox);

        setBackground(new Color(248, 246, 244));

    }

    @Override
    public void refresh(RefreshEvent evt) {
        statusComboBox.setSelectedItem("WAITING");
    }
}
