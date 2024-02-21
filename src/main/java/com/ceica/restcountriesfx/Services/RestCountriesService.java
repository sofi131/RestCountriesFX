package com.ceica.restcountriesfx.Services;
import com.google.gson.Gson;
import com.ceica.restcountriesfx.Models.CountryDAO;
import com.ceica.restcountriesfx.Models.CountryDTO;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestCountriesService implements IRestCountries {
    //las diferentes regiones (europa...)
    @Override
    public String[] getRegions() {
        List<String> regions=new ArrayList<>();
        String url = "https://restcountries.com/v3.1/all";
        try {
            String datos=getDataUrl(url);
            Gson gson=new Gson();
            CountryDAO[] objects= gson.fromJson(datos,CountryDAO[].class);
            for (CountryDAO countryDAO:objects){
                if(! regions.contains(countryDAO.region)){
                    regions.add(countryDAO.region);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] regionArray=new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionArray[i]=regions.get(i);
        }
        return regionArray;
    }
    //Lista de los países
    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        String url="https://restcountries.com/v3.1/region/"+region;
        List<CountryDTO> countryDTOList=new ArrayList<>();
        try {
            String datos=getDataUrl(url);
            Gson gson=new Gson();
            List<CountryDAO> objects= gson.fromJson(datos,new TypeToken<List<CountryDAO>>(){}.getType());

            for (CountryDAO countryDAO:objects){
                countryDTOList.add(CountryDTO.from(countryDAO));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countryDTOList;
    }
    //para conseguir los datos del país
    @Override
    public CountryDTO getCountryByName(String name) {
        String nameFormatted=name.split(" ")[0];
        String url="https://restcountries.com/v3.1/name/"+nameFormatted;
        CountryDTO countryDTO=null;
        try {
            String datos=getDataUrl(url);
            Gson gson=new Gson();
            CountryDAO[] countryDAO=gson.fromJson(datos,CountryDAO[].class);
            countryDTO=CountryDTO.from(countryDAO[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return countryDTO;
    }

    @Override
    public CountryDTO getCountryByCca3(String cca3) {

        String url="https://restcountries.com/v3.1/alpha/"+cca3;
        CountryDTO countryDTO=null;
        try {
            String datos=getDataUrl(url);
            Gson gson=new Gson();
            CountryDAO[] countryDAO=gson.fromJson(datos,CountryDAO[].class);
            countryDTO=CountryDTO.from(countryDAO[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return countryDTO;
    }

    private String getDataUrl(String url) throws IOException {
        URL obj = new URL(url);
        // Abrimos una conexión HTTP
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Establecemos el método de petición como GET
        con.setRequestMethod("GET");
        // Creamos un BufferedReader para leer la respuesta del servidor
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        // Creamos un StringBuilder para almacenar la respuesta
        StringBuilder response = new StringBuilder();
        // Leemos línea por línea la respuesta y la añadimos al StringBuilder
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        // Cerramos el BufferedReader
        in.close();
        return response.toString();
    }
}