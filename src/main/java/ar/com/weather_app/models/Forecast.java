package ar.com.weather_app.models;

import ar.com.weather_app.repositories.dao.ForecastDAO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.lang.NonNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Forecast {
    private Float temperature;
    private String description;
    private String location;
    private Integer precipitation;
    private String metric;

    public Forecast(){}

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public static Forecast from (@NonNull ForecastDAO forecast, @NonNull String location){
        Forecast newForecast = new Forecast();
        newForecast.location = location;
        newForecast.description = forecast.getDescription();
        newForecast.precipitation = forecast.getPrecipitation();
        newForecast.temperature = forecast.getTemperature();
        newForecast.metric = forecast.getMetric();
        return newForecast;
    }
}
