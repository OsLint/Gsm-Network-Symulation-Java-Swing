package Graphics;

import javax.swing.*;

public class Window extends JFrame {
    private static Window instance;

    private Window() {
    super("GuiSet03");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1280,720);
    setVisible(true);


    }

    /**
     * Algorytm Singleton
     * @return Przy każdym wywołaniu zwraca ten sam obiekt klasy Graphics.Window, dzięki czemu mamy pewność, że w programie
     * powstanie tylko jeden obiekt klasy Graphics.Window.
     */
    public static Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }
}
