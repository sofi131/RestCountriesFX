module com.ceica.restcountriesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ceica.restcountriesfx to javafx.fxml;
    exports com.ceica.restcountriesfx;
}