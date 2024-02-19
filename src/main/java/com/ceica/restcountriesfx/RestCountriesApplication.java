package com.ceica.restcountriesfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RestCountriesApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestCountriesApplication.class.getResource("restcountries.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RestCountries");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}