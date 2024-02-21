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
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
//cambiamos el método deprecado
    private String getDataUrl(String url) throws IOException {
        //Crear un cliente httpclient
        HttpClient client = HttpClient.newHttpClient();
        //crear una solicitud http get
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
            //para que pinte el cuerpo
           // System.out.println(response.body());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}