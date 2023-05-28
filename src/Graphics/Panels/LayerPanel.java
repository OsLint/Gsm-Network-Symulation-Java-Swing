package Graphics.Panels;

import Graphics.Visualisations.LayerVisual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static Graphics.Window.layers;
import static Graphics.Window.showInfoDialog;

public class LayerPanel extends PanelTemplate {
    JButton deleteButton;

    /**
     * Konstruktor klasy LayerPanel.
     * Inicjalizuje komponenty i ustawia początkowe warstwy.
     *
     * @param title Tytuł panelu.
     */
    public LayerPanel(String title) {
        super(title);

        //Inicjalizacja komponentów
        deleteButton = new JButton("-");
        JPanel buttonPanel = new JPanel();

        //Add Button
        addButton.setText("+");
        addButton.setForeground(new Color(0, 255, 0));

        //Delete Button
        deleteButton.setForeground(new Color(255, 0, 0));
        deleteButton.addActionListener(this::handleDeleteButtonAction);

        //ViewportView
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.X_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //ButtonPanel
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(addButton, BorderLayout.WEST);
        buttonPanel.add(deleteButton, BorderLayout.EAST);

        //TitleTextField
        titleTextField.setHorizontalAlignment(SwingConstants.CENTER);

        add(titleTextField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        //Dodanie początkowych warstw
        layers.add(new LayerVisual("BTS"));
        layers.add(new LayerVisual("BSC"));
        layers.add(new LayerVisual("BTS"));


        updateViewportView();
    }

    /**
     * Obsługuje akcję naciśnięcia przycisku "Add".
     * Aktualizuje widok aplikacji po wykonaniu operacji.
     *
     * @param e Obiekt reprezentujący zdarzenie akcji.
     */
    @Override
    protected void handleAddButtonAction(ActionEvent e) {
        // Tworzenie nowej warstwy
        LayerVisual newLayer = new LayerVisual("BSC");
        // Obliczenie indeksu, w którym ma zostać wstawiona nowa warstwa
        int insertIndex = layers.size() / 2;
        // Dodanie nowej warstwy do kolekcji
        layers.add(insertIndex, newLayer);
        // Aktualizacja widoku aplikacji
        updateViewportView();
    }

    /**
     * Obsługuje akcję naciśnięcia przycisku "Delete".
     * Sprawdza warunki i podejmuje odpowiednie działania w zależności od stanu warstw.
     * Aktualizuje widok aplikacji po wykonaniu operacji.
     *
     * @param e Obiekt reprezentujący zdarzenie akcji.
     */
    private void handleDeleteButtonAction(ActionEvent e) {
        // Sprawdza, czy istnieje wystarczająca liczba warstw w kolekcji
        if (layers.size() > 2) {
            LayerVisual lastLayer = layers.get(layers.size() - 1);
            String layerType = lastLayer.getTitle();

            // Sprawdza, czy ostatnia warstwa to "BTS"
            if (layerType.equals("BTS")) {
                int bscCount = 0;

                // Liczy warstwy o tytule "BSC" przed ostatnią warstwą
                for (int i = layers.size() - 2; i >= 0; i--) {
                    if (layers.get(i).getTitle().equals("BSC")) {
                        bscCount++;
                    }
                }
                // Jeśli istnieje więcej niż 1 warstwa "BSC", usuwa jedną z warstw "BSC"
                if (bscCount > 1) {
                    for (int i = layers.size() - 1; i >= 0; i--) {
                        if (layers.get(i).getTitle().equals("BSC")) {
                            turrnOFFlayer(layers.get(i));
                            layers.remove(i);
                            break;
                        }
                    }
                } else {
                    // Wyświetla dialog informacyjny o błędzie minimalnej ilości warstw
                    showInfoDialog("Error", "Osiągnięta minimalną ilość warstw...", null);
                }
            } else {
                // Jeśli ostatnia warstwa to inny typ, usuwa ją
                turrnOFFlayer(layers.get(layers.size() - 1));
                layers.remove(layers.size() - 1);

            }
            // Aktualizuje widok aplikacji
            updateViewportView();
        }
    }

    /**
     * Metoda wyłącza stacje z wybranej warstwy.
     *
     * @param layerVisual Warstwa, której stacje mają zostać wyłączone.
     */
    void turrnOFFlayer(LayerVisual layerVisual) {
        // Wyłączenie stacji na warstwie
        layerVisual.turnOff();
    }

    /**
     * Metoda aktualizuje widok aplikacji.
     */
    private void updateViewportView() {
        // Usunięcie wszystkich komponentów z widoku
        viewportView.removeAll();

        // Dodanie warstw do widoku
        for (LayerVisual layer : layers) {
            viewportView.add(layer);
        }
        // Revalidacja i odrysowanie widoków
        viewportView.revalidate();
        viewportView.repaint();
        // Revalidacja i odrysowanie panelu przewijania
        deviceScrollPane.revalidate();
        deviceScrollPane.repaint();
    }

}
