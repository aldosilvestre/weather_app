package ar.com.weather_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties
public class ForecastDTO {

    @JsonProperty("IconPhrase")
    private String description;
    @JsonProperty("PrecipitationProbability")
    private Integer precipitation;
    @JsonProperty("Temperature")
    private TemperatureDTO temperature;
    @JsonProperty("DateTime")
    private Date dateTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    public TemperatureDTO getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureDTO temperature) {
        this.temperature = temperature;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
