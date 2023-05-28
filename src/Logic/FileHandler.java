package Logic;


import Graphics.Window;


import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Klasa FileHandler służy do tworzenia pliku binarnego zawierającego informacje o każdym VBD,
 * w tym ilość wysłanych wiadomości oraz treść wiadomości.
 */
public class FileHandler {
    /**
     * Tworzy plik binarny zawierający informacje o każdym VBD,
     * w tym ilość wysłanych wiadomości oraz treść wiadomości.
     */
    public FileHandler() {
        try {
            // Tworzenie strumienia do zapisu pliku binarnego
            FileOutputStream fos = new FileOutputStream("vbd_info.bin");

            int totalMessagesSent;
            String messageContent;

            for (int i = 0; i < Window.VBDlist.size(); i++) {
                // Pobieranie informacji o ilości wysłanych wiadomości i treści wiadomości
                totalMessagesSent = Window.VBDlist.get(i).getMessageSendedCounter();
                messageContent = Window.VBDlist.get(i).getMessage().getContent();

                // Zapisywanie informacji o ilości wysłanych wiadomości do pliku binarnego
                fos.write(totalMessagesSent);

                // Zapisywanie treści wiadomości jako sekwencji bajtów do pliku binarnego
                fos.write(messageContent.getBytes());
            }

            // Zamykanie strumienia po zapisie wszystkich danych
            fos.close();
        } catch (IOException e) {
            // Obsługa błędów I/O
            throw new RuntimeException(e);
        }
    }
}