package ar.com.weather_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDTO {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("LocalizedName")
    private String countryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
