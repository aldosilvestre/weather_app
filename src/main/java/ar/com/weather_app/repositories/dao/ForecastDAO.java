package ar.com.weather_app.repositories.dao;

import ar.com.weather_app.dto.ForecastDTO;
import ar.com.weather_app.dto.LocationDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Forecast")
public class ForecastDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long locationKey;
    @Column
    private String description;
    @Column
    private Float temperature;
    @Column
    private String metric;
    @Column
    private Integer precipitation;
    @Column(unique=true)
    private Date dateTime;

    public ForecastDAO(){}
    private ForecastDAO(ForecastDTO dto, LocationDTO location){
        this.locationKey = location.getKey();
        this.description = dto.getDescription();
        this.precipitation = dto.getPrecipitation();
        this.dateTime = dto.getDateTime();
        this.temperature = dto.getTemperature().getTemp();
        this.metric = dto.getTemperature().getUnit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocationKey() {
        return locationKey;
    }

    public void setLocationKey(Long locationKey) {
        this.locationKey = locationKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public static List<ForecastDAO> from(List<ForecastDTO> forecasts, LocationDTO location) {
        return forecasts.stream().map( f -> new ForecastDAO(f, location)).collect(Collectors.toList());
    }

}
