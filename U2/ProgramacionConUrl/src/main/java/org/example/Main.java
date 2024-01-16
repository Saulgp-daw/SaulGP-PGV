package org.example;
import java.net.*;
import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String saveDir = "/home/dam2/Descargas/";
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


    }
    private static String getFileName(String fileUrl){
        return fileUrl.substring(fileUrl.lastIndexOf("/")+1, fileUrl.length());
    }
}