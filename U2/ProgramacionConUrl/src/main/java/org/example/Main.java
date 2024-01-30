package org.example;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SocketException, IOException {
        /*String saveDir = "/home/dam2/Descargas/";
        try{
            URL url = new URL("https://pdfobject.com/pdf/sample.pdf");
            URLConnection urlCon = url.openConnection();
            String contentType = urlCon.getContentType();


            try(BufferedInputStream in = new BufferedInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(saveDir+getFileName(url+""))
            ){
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }catch (IOException e) {
                System.out.println(e);
            }


        }catch (MalformedURLException me) {
            System.out.println("URL erronea");
        } catch (IOException ioe) {
            System.out.println("Error IO");
        }

*/
        FTPClient client = new FTPClient();

        String servidorFTP = "localhost";

        String User = "dam2";

        String Password = "J4V4";

        System.out.println("Nos conectamos a: " + servidorFTP);

        client.connect(servidorFTP);

        client.login(User,Password);

        FTPFile[] lista = client.listFiles();

        for (int i = 0; i < lista.length; i++) {

            System.out.println(lista[i]);

        }

        System.out.println(client.getReplyString());

        int res = client.getReplyCode();

        if(!FTPReply.isPositiveCompletion(res)){

            client.disconnect();

            System.out.println("Conexión rechazada:" + res);

            System.exit(0);

        }

        client.disconnect();

        System.out.println("Conexión finalizada.");

    }
}