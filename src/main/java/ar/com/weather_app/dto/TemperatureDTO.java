package ar.com.weather_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureDTO {
    @JsonProperty("Value")
    private Float temp;
    @JsonProperty("Unit")
    private String unit;

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
