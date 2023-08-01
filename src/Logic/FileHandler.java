package Logic;


import Graphics.Window;


import java.io.FileOutputStream;
import java.io.IOException;


public class FileHandler {

    public FileHandler() {
        try {

            FileOutputStream fos = new FileOutputStream("vbd_info.bin");

            int totalMessagesSent;
            String messageContent;

            for (int i = 0; i < Window.VBDlist.size(); i++) {

                totalMessagesSent = Window.VBDlist.get(i).getMessageSendedCounter();
                messageContent = Window.VBDlist.get(i).getMessage().getContent();


                fos.write(totalMessagesSent);


                fos.write(messageContent.getBytes());
            }


            fos.close();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}