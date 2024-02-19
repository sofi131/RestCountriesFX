package com.ceica.restcountriesfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RestCountriesController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Bienvenido a la app de países");
    }
}