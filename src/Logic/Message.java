package Logic;

public class Message {

    private int adress;

    private final String content;


    public Message(String content) {
        this.content = content;
    }


    public void setAdress(int adress) {
        this.adress = adress;
    }



    public int getAdress() {
        return adress;
    }


    public String getContent() {
        return content;
    }


    @Override
    public String toString() {
        return content;
    }
}
