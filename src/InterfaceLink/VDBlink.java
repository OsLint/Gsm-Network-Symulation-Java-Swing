package InterfaceLink;


public interface VDBlink {
    void setMessage(String message);
    void setFrequency(int frequency);
    void setIsWorking(boolean working);
    void setIsWaiting(boolean waiting);
    String getMessage ();
    int getFrequency();
    int getID();
    boolean getIsWorking();
    boolean getIsWaiting();


}
