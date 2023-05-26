package Exeptions;

public class NumberNotFoundExeption extends Exception{
    private String reciverNumber;
    private String name;
    public NumberNotFoundExeption (String reciverNumber) {
        super("Not existing number of Receiving Device: " + reciverNumber);
        this.reciverNumber = reciverNumber;
    }

    public String getReciverNumber() {
        return reciverNumber;
    }

    public String getName() {
        return name;
    }
}
