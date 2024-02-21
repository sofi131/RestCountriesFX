module com.ceica.restcountriesfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.ceica.restcountriesfx to javafx.fxml;
    exports com.ceica.restcountriesfx;
    exports com.ceica.restcountriesfx.Models;
}