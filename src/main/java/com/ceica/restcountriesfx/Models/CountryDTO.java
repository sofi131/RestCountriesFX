package com.ceica.restcountriesfx.Models;

public class CountryDTO {
    private String name;
    private String flag;
    private String region;
    private String capital;
    private String coin;
    private int population;
    private String cca3;

    public CountryDTO() {
    }
    public static CountryDTO from(CountryDAO countryDAO){
        CountryDTO countryDTO=new CountryDTO();
        countryDTO.setName(countryDAO.name.common);
        countryDTO.setFlag(countryDAO.flags.png);
        countryDTO.setPopulation(countryDAO.population);
        String capital="";
        if (countryDAO.capital!=null)
            if (countryDAO.capital.length>0)
                capital=countryDAO.capital[0];
        countryDTO.setCapital(capital);
        String coin="";
        if (countryDAO.currencies!=null){
            String keyCurrency= (String) countryDAO.currencies.keySet().toArray()[0];
            coin=countryDAO.currencies.get(keyCurrency).name;
        }
        countryDTO.setCoin(coin);
        countryDTO.setCca3(countryDAO.cca3);
        return countryDTO;
    }
//--------------------------------------GETTER Y SETTER--------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
//-------------------------------fifa--------------------------

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }
}
