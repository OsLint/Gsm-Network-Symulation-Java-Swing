package Graphics.Panels;

import Graphics.Visualisations.LayerVisual;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static Graphics.Window.layers;
import static Graphics.Window.showInfoDialog;

public class LayerPanel extends PanelTemplate {
    JButton deleteButton;
    public LayerPanel(String title) {
        super(title);
        addButton.setText("+");
        deleteButton = new JButton("-");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        deleteButton.setMargin(new Insets(5, 10, 5, 10));
        addButton.setMargin(new Insets(5, 10, 5, 10));
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.X_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(addButton, BorderLayout.WEST);
        buttonPanel.add(deleteButton, BorderLayout.EAST);

        deleteButton.setForeground(new Color(255, 0, 0));
        addButton.setForeground(new Color(0, 255, 0));

        titleTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleTextField, BorderLayout.NORTH);

        deleteButton.addActionListener(this::handleDeleteButtonAction);


        add(buttonPanel, BorderLayout.SOUTH);
        layers.add(new LayerVisual("BTS"));
        layers.add(new LayerVisual("BSC"));
        layers.add(new LayerVisual("BTS"));


        updateViewportView();
    }

    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        LayerVisual newLayer = new LayerVisual("BSC");
        int insertIndex = layers.size() / 2; // Dodawanie na środek

        layers.add(insertIndex, newLayer);

        updateViewportView();
    }

    private void updateViewportView() {
        viewportView.removeAll();

        for (LayerVisual layer : layers) {
            viewportView.add(layer);
        }

        viewportView.revalidate();
        viewportView.repaint();
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }

    private void handleDeleteButtonAction(ActionEvent e) {
        if (layers.size() > 2) {
            LayerVisual lastLayer = layers.get(layers.size() - 1);
            String layerType = lastLayer.getTitle();

            if (layerType.equals("BTS")) {
                int bscCount = 0;

                for (int i = layers.size() - 2; i >= 0; i--) {
                    if (layers.get(i).getTitle().equals("BSC")) {
                        bscCount++;
                    }
                }

                if (bscCount > 1) {
                    for (int i = layers.size() - 1; i >= 0; i--) {
                        if (layers.get(i).getTitle().equals("BSC")) {
                            turrnOFFlayer(layers.get(i));
                            layers.remove(i);
                            break;
                        }
                    }
                }else {
                    showInfoDialog("Error","Osiągnięta minimalną ilość warstw...",null);
                }
            } else {
                turrnOFFlayer(layers.get(layers.size() - 1));
               layers.remove(layers.size() - 1);

            }

            updateViewportView();
        }
    }

    void turrnOFFlayer (LayerVisual layerVisual) {
        layerVisual.turnOff();
    }


}
