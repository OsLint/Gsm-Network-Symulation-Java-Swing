/**
 * GUI SET 03 "Sieć GSM"
 * Klasa główna programu, inicjalizuje okno aplikacji.
 * Autor: Oskar Kalbarczyk s27773 37c
 */

import Graphics.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                Main::new
        );
    }

    public Main() {
         new Window();
    }
}