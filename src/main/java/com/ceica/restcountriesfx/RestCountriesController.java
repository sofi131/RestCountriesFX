package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.Models.CountryDTO;
import com.ceica.restcountriesfx.Services.FakeRestCountriesService;
import com.ceica.restcountriesfx.Services.FakeRestCountriesService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class RestCountriesController {
    @FXML
    protected ComboBox comboRegion;
    @FXML
    protected TableView<CountryDTO> tblCountries;
    @FXML
    protected TableColumn<CountryDTO, String> countryNameColumn;
    @FXML
    protected ImageView imgFlag;
    @FXML
    protected TextField txtCountryName;
    @FXML
    protected TextField txtCountryPopulation;
    @FXML
    protected TextField txtCountryCapital;
    @FXML
    protected TextField txtCountryCoin;
    private ObservableList<CountryDTO> observableList = FXCollections.observableArrayList();

    //método inicializar
    @FXML
    public void initialize() {
        FakeRestCountriesService fakeRestCountriesService = new FakeRestCountriesService();
        comboRegion.getItems().addAll(fakeRestCountriesService.getRegions());
        //para que clique la region correspondiente
        comboRegion.setOnAction(e -> {
            if (comboRegion.getSelectionModel().getSelectedItem() != null) {
                String region = comboRegion.getSelectionModel().getSelectedItem().toString();
                observableList.clear();
                observableList.addAll(fakeRestCountriesService.getCountriesByRegion(region));
                tblCountries.setItems(observableList);
            }
        });
        tblCountries.setOnMouseClicked(e->{
            //nombre del país que se ha clicado
            String countryName=tblCountries.getSelectionModel().getSelectedItem().getName();
            //objeto country con toda la información (bandera y demás)
            CountryDTO countryDTO=fakeRestCountriesService.getCountryByName(countryName);

        });
        countryNameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    }

    @FXML
    public void btnClear(ActionEvent actionEvent) {
        observableList.clear();
        tblCountries.refresh();
        comboRegion.getSelectionModel().clearSelection();
    }
}


