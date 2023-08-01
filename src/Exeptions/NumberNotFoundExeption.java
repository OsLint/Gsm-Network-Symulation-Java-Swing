package Exeptions;


public class NumberNotFoundExeption extends Exception {
    private final int receiverNumber;

    public NumberNotFoundExeption(int receiverNumber) {
        super("Not existing number of Receiving Device: " + receiverNumber);
        this.receiverNumber = receiverNumber;
    }

    public int getReceiverNumber() {
        return receiverNumber;
    }

}
