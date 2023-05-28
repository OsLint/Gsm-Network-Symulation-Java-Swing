package Exeptions;

/**
 * Wyjątek rzucany w przypadku nieistniejącego numeru odbiorcy.
 */
public class NumberNotFoundExeption extends Exception {
    private final int receiverNumber;
    /**
     * Konstruktor klasy NumberNotFoundException.
     * Inicjalizuje wyjątek z informacją o nieistniejącym numerze odbiorcy.
     *
     * @param receiverNumber Numer odbiorcy, który nie został znaleziony.
     */
    public NumberNotFoundExeption(int receiverNumber) {
        super("Not existing number of Receiving Device: " + receiverNumber);
        this.receiverNumber = receiverNumber;
    }
    /**
     * Metoda zwraca numer odbiorcy, który nie został znaleziony.
     *
     * @return Numer odbiorcy.
     */
    public int getReceiverNumber() {
        return receiverNumber;
    }

}
