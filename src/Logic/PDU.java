package Logic;

public class PDU {

    public static String encodeSms(String serviceCenterAddress, String originatingAddress, String message){
        byte[] messageBytes = message.getBytes();

        int pduLength = 1 + serviceCenterAddress.length() / 2 + 7 + (messageBytes.length * 2);

        return "";
    }

    public static String encodeAdress(String adress){
        return "";
    }

    public static String encodeMessage(String message){
        return "";
    }

    public static String decodeAdress(Byte adress){
        return "";
    }


}
