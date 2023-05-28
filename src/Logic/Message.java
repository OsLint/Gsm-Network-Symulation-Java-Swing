package Logic;

/**
 * Reprezentuje wiadomość, która może być przesyłana pomiędzy stacjami.
 */
public class Message {
    // Adres docelowy wiadomości
    private int adress;
    // Treść wiadomości
    private final String content;

    /**
     * Tworzy nową wiadomość o podanej treści.
     *
     * @param content treść wiadomości
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * Ustawia adres docelowy wiadomości.
     *
     * @param adress adres docelowy
     */
    public void setAdress(int adress) {
        this.adress = adress;
    }


    /**
     * Zwraca adres docelowy wiadomości.
     *
     * @return adres docelowy
     */
    public int getAdress() {
        return adress;
    }

    /**
     * Zwraca zawartość wiadomości
     * @return zawartość wiadomości
     */
    public String getContent() {
        return content;
    }

    /**
     * Zwraca treść wiadomości.
     *
     * @return treść wiadomości
     */


    @Override
    public String toString() {
        return content;
    }
}
