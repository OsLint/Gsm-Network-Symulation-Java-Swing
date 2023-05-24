package Graphics.Panels;

import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.StationVisual;
import Logic.StationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LayerPanel extends PanelTemplate{

    //ArrayList<LayerVisual> layers = new ArrayList<>();
    JButton deleteButton;
    LayerVisual leftLayer;
    LayerVisual rightLayer;

    public LayerPanel(String title) {
        super(title);

       leftLayer = new LayerVisual("BTS");
       rightLayer = new LayerVisual("BTS");

        addButton.setText("+");
        deleteButton = new JButton("-");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        deleteButton.setMargin(new Insets(5, 10, 5, 10));
        addButton.setMargin(new Insets(5, 10, 5, 10));

        viewportView.setLayout(new BoxLayout(viewportView,BoxLayout.X_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(addButton,BorderLayout.WEST);
        buttonPanel.add(deleteButton,BorderLayout.EAST);

        // Wyświetlanie kolorów przycisków

        deleteButton.setForeground(new Color(255,0,0));
        addButton.setForeground(new Color(0,255,0));

        titleTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleTextField, BorderLayout.NORTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteButtonAction(e);
            }
        });


        viewportView.add(rightLayer,BorderLayout.EAST);
        viewportView.add(leftLayer,BorderLayout.WEST);
        add(buttonPanel,BorderLayout.SOUTH);


    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
    //2DO:
        LayerVisual newLayer = new LayerVisual("BSC");
        viewportView.add(newLayer,BorderLayout.CENTER);
        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();

    }
    void handleDeleteButtonAction(ActionEvent e) {
      //2DO:
    }
}
