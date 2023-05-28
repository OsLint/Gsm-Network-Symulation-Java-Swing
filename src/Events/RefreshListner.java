package Events;
/**
 * Interfejs RefreshListener.
 * Definiuje metodę wywoływaną w celu odświeżenia listy.
 */
public interface RefreshListner {
    /**
     * Metoda refresh.
     * Wywoływana w celu odświeżenia listy na podstawie zdarzenia RefreshEvent.
     *
     * @param evt Zdarzenie odświeżenia.
     */
    void refresh(RefreshEvent evt);
}
