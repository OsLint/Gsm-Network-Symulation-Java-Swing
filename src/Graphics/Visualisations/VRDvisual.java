package Graphics.Visualisations;

import Events.RefreshEvent;
import Events.RefreshListner;
import InterfaceLink.VRDlink;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static Graphics.Window.VRDlist;


public class VRDvisual extends JPanel implements RefreshListner {
    private final JLabel messageCounter;
    private final JCheckBox clearTimeCheckBox;
    private final VRDlink vrDlink;


    public VRDvisual(VRDlink vrDlink) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));


        this.vrDlink = vrDlink;
        JTextField idTextField = new JTextField("Phone Number: +48" + vrDlink.getID());
        JButton stopButton = new JButton("Stop");
        messageCounter = new JLabel(
                "Message count: " +
                        vrDlink.getReceivedMessageCount()
        );
        clearTimeCheckBox = new JCheckBox("Reset Counter");


        clearTimeCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        clearTimeCheckBox.addActionListener(e -> {
            if (clearTimeCheckBox.isSelected()) {
                vrDlink.startResetMessageCount();
            } else {
                vrDlink.stopResetMessageCount();
            }
        });


        messageCounter.setAlignmentX(Component.LEFT_ALIGNMENT);


        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));



        idTextField.setPreferredSize(new Dimension(150, 50));
        idTextField.setBackground(new Color(227, 244, 244));
        idTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        idTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        idTextField.setFont(new Font("Arial", Font.BOLD, 20));
        idTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        idTextField.setEditable(false);


        stopButton.setForeground(new Color(255, 0, 0));
        stopButton.addActionListener(e -> {
            vrDlink.setIsWorking(false);
            setVisible(false);
            for (int i = 0; i < VRDlist.size(); i++) {
                if (VRDlist.get(i) == vrDlink) {
                    VRDlist.remove(vrDlink);
                }
            }
        });
        stopButton.setAlignmentX(Component.LEFT_ALIGNMENT);


        add(idTextField);
        add(stopButton);
        add(messageCounter);
        add(clearTimeCheckBox);

        setBackground(new Color(248, 246, 244));


    }


    @Override
    public void refresh(RefreshEvent evt) {
        messageCounter.setText("Message count: " + vrDlink.getReceivedMessageCount());

    }
}
