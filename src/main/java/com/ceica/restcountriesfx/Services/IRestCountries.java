package com.ceica.restcountriesfx.Services;

import com.ceica.restcountriesfx.Models.CountryDTO;

import java.util.List;

public interface IRestCountries {
    public String[] getRegions();
    //data transfer object
    public List<CountryDTO> getCountriesByRegion(String region);
    public CountryDTO getCountryByName(String name);
}
