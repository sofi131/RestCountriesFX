package com.ceica.restcountriesfx.Services;

import com.ceica.restcountriesfx.Models.CountryDTO;

import java.util.ArrayList;
import java.util.List;

public class FakeCountriesService implements IRestCountries {

    @Override
    public String[] getRegions() {
       return new  String[] {"Europe","Asia","America","Africa","Oceania","Antartida"};

    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        List<CountryDTO> countryDTOList=new ArrayList<>();
        //creamos objetos y cada uno tiene que ser diferente
        CountryDTO countryDTO=new CountryDTO();
        countryDTO.setName("Spain");
        CountryDTO countryDTO1=new CountryDTO();
        countryDTO.setName("France");
        countryDTOList.add(countryDTO1);
        return null;
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        CountryDTO countryDTO=new CountryDTO();
        countryDTO.setName("Spain");
        countryDTO.setCapital("Madrid");
        countryDTO.setCoin("EUR");
        countryDTO.setRegion("Europe");
        countryDTO.setPopulation(38000000);
        countryDTO.setFlag("https://flagcdn.com/w320/es.png");
        return countryDTO;
    }
}