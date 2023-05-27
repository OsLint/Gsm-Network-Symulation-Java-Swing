package Exeptions;

public class NumberNotFoundExeption extends Exception{
    private int reciverNumber;
    private String name;
    public NumberNotFoundExeption (int reciverNumber) {
        super("Not existing number of Receiving Device: " + reciverNumber);
        this.reciverNumber = reciverNumber;
    }


    public int getReciverNumber() {
        return reciverNumber;
    }

    public String getName() {
        return name;
    }
}
