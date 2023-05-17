package Graphics;

import InterfaceLink.VDBlink;
import Logic.VDB;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VDBpanel extends JPanel {

    private JScrollPane deviceScrollPane;
    private JButton addButton;


    public VDBpanel (int height,int width) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));

        addButton = new JButton("Add");
        deviceScrollPane = new JScrollPane();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog("Wprowadź wiadomość:");

                // Utworzenie nowego urządzenia nadawczego na podstawie wprowadzonej wiadomości
                VDBvisual newVDB = new VDBvisual(new VDB(message));
                // Dodanie nowego urządzenia do panelu z urządzeniami
                add(newVDB);

                revalidate();
                repaint();
            }
        });



        add(addButton, BorderLayout.NORTH);
        add(deviceScrollPane,BorderLayout.CENTER);





    }

    class VDBvisual extends JPanel {

        private VDBlink vdBlink;

        private JLabel messageLabel;
        private JSlider frequencySlider;
        private JButton stopButton;
        private JTextField idTextField;
        private JComboBox statusComboBox;

        public VDBvisual (VDBlink vdBlink) {
            setSize(new Dimension(VDBpanel.WIDTH,100));
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

            this.vdBlink = vdBlink;

            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

            //Inicjalizacja komponentów
            messageLabel = new JLabel("Wiadomość: " + vdBlink.getMessage());
            frequencySlider = new JSlider(1,30,vdBlink.getFrequency());
            stopButton = new JButton("Stop");
            idTextField = new JTextField("ID: " + vdBlink.getID());
            statusComboBox = new JComboBox(new String[] {"ACTIVE", "WAITING"});

            statusComboBox.setBackground(Color.blue);


            //Stop button
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   vdBlink.setIsWorking(false);
                   setVisible(false);
                }
            });

            //Zmiana Frequency za pomocą slidera
            frequencySlider.addChangeListener(e -> {
                vdBlink.setFrequency(frequencySlider.getValue());
            });


            //Dodanie komponentów do panelu
            add(messageLabel);
            add(frequencySlider);
            add(stopButton);
            add(idTextField);
            add(statusComboBox);



            setPreferredSize(new Dimension(250, 150));
            setBackground(Color.GREEN);


            //2do:
            //vdv startWorkingthread();


        }



    }

}
