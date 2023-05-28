package Graphics.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Klasa PanelTemplate.
 * Abstrakcyjna klasa szablonowa dla paneli.
 */
public abstract class PanelTemplate extends JPanel {
    protected JScrollPane deviceScrollPane;
    protected JButton addButton;
    protected JLabel titleTextField;
    protected JPanel viewportView;

    protected String title;

    /**
     * Konstruktor klasy PanelTemplate.
     * Inicjalizuje panel z podanym tytułem.
     *
     * @param title Tytuł panelu.
     */
    public PanelTemplate(String title) {
        this.title = title;
        setPreferredSize(new Dimension(
                (Toolkit.getDefaultToolkit().getScreenSize().width) / 3,
                Toolkit.getDefaultToolkit().getScreenSize().height
        ));
        setLayout(new BorderLayout());

        //Inicjalizacja komponentów
        titleTextField = new JLabel(title);
        addButton = new JButton("Add");
        deviceScrollPane = new JScrollPane();
        viewportView = new JPanel();

        //TitleTextField
        titleTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        titleTextField.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        //ViewPort
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.Y_AXIS));

        //Device ScrollPane
        deviceScrollPane.setViewportView(viewportView);
        deviceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deviceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Obsługa zdarzenia kliknięcia przycisku "Add"
        addButton.addActionListener(this::handleAddButtonAction);

        // Dodawanie komponentów do panelu
        add(titleTextField, BorderLayout.NORTH);
        add(deviceScrollPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);


        this.setBackground(new Color(196, 223, 223));
        viewportView.setBackground(new Color(210, 223, 223));
        deviceScrollPane.setBackground(new Color(210, 223, 223));


    }

    /**
     * Metoda abstrakcyjna do obsługi zdarzenia kliknięcia przycisku "Add".
     *
     * @param e Obiekt reprezentujący zdarzenie akcji.
     */
    protected abstract void handleAddButtonAction(ActionEvent e);

}
