package com.ceica.restcountriesfx.Services;

import com.ceica.restcountriesfx.Models.CountryDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RestCountriesService implements IRestCountries {
    @Override
    public String[] getRegions() {
        String url="https://restcountries.com/v3.1/all";

        return new String[0];
    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        return null;
    }

    @Override
    public CountryDTO getCountryByName(String name) {
        return null;
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
        //devolvemos la respuesta
        return response.toString();
    }
}
