package org.example.proyectoftp;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class proyecto_controller implements Initializable {

	String servidorFTP = "localhost";
   String User = "dam2";
   String password = "J4V4";
   FTPClient client = new FTPClient();
    public Button btnSubir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void subirArchivo(ActionEvent actionEvent) {
        System.out.println("Hola");
    }
}
