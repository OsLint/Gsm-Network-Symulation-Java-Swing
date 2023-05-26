package Logic;

public class Message {
    private String adress;
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdress() {
        return adress;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return  content;

    }
}
