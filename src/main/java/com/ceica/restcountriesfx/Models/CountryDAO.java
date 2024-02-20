package com.ceica.restcountriesfx.Models;

public class CountryDAO {
    public Name name;
    public String[] capital;
    public String region;
    public int population;
    public Flag flags;
    public Currencies currencies;

}
//nombre
class Name{
    public String common;
}
//bandera
class Flag{
    public String png;
}
//moneda
class Currency{
    public String name;
    public String symbol;
}

class Currencies{
    public String currencyCode;
    public Currency currency;
}
