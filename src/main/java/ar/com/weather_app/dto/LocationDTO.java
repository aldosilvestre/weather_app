package ar.com.weather_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty("Key")
    private Long key;
    @JsonProperty("LocalizedName")
    private String localizedName;
    @JsonProperty("Country")
    private CountryDTO country;
    @JsonProperty("TimeZone")
    private TimeZoneDTO timezone;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public TimeZoneDTO getTimezone() {
        return timezone;
    }

    public void setTimezone(TimeZoneDTO timezone) {
        this.timezone = timezone;
    }
}