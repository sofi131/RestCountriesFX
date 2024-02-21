package com.ceica.restcountriesfx;

import com.ceica.restcountriesfx.Models.CountryDTO;
import com.ceica.restcountriesfx.Services.FakeRestCountriesService;
import com.ceica.restcountriesfx.Services.FakeRestCountriesService;
import com.ceica.restcountriesfx.Services.RestCountriesService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
        RestCountriesService fakeRestCountriesService = new RestCountriesService();

        comboRegion.getItems().addAll(fakeRestCountriesService.getRegions());
        comboRegion.setOnAction(e -> {
            if (comboRegion.getSelectionModel().getSelectedItem() != null) {
                String region = comboRegion.getSelectionModel().getSelectedItem().toString();
                observableList.clear();
                observableList.addAll(fakeRestCountriesService.getCountriesByRegion(region));
                tblCountries.setItems(observableList);
            }
        });
        tblCountries.setOnMouseClicked(e->{
            String countrycca3=tblCountries.getSelectionModel().getSelectedItem().getCca3();
            CountryDTO countryDTO=fakeRestCountriesService.getCountryByCca3(countrycca3);
            txtCountryName.setText(countryDTO.getName());
            txtCountryCapital.setText(countryDTO.getCapital());
            txtCountryCoin.setText(countryDTO.getCoin());
            txtCountryPopulation.setText(String.valueOf(countryDTO.getPopulation()));
            Image imgen=new Image(countryDTO.getFlag());
            imgFlag.setImage(imgen);
        });
        //para navegar en la tabla
        tblCountries.setOnKeyPressed(e ->{
            String countrycca3=tblCountries.getSelectionModel().getSelectedItem().getCca3();
            CountryDTO countryDTO=fakeRestCountriesService.getCountryByCca3(countrycca3);
            txtCountryName.setText(countryDTO.getName());
            txtCountryCapital.setText(countryDTO.getCapital());
            txtCountryCoin.setText(countryDTO.getCoin());
            txtCountryPopulation.setText(String.valueOf(countryDTO.getPopulation()));
            Image imgen=new Image(countryDTO.getFlag());
            imgFlag.setImage(imgen);
        });
        countryNameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    }

    @FXML
    public void btnClear(ActionEvent actionEvent) {
        observableList.clear();
        tblCountries.refresh();
        comboRegion.getSelectionModel().clearSelection();
        txtCountryName.setText("");
        txtCountryCoin.setText("");
        txtCountryPopulation.setText("");
        txtCountryCapital.setText("");
        imgFlag.setImage(null);
    }
}


