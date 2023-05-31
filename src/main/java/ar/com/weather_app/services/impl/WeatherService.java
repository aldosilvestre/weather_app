package ar.com.weather_app.services.impl;

import ar.com.weather_app.dto.ForecastDTO;
import ar.com.weather_app.dto.LocationDTO;
import ar.com.weather_app.exceptions.DomainException;
import ar.com.weather_app.exceptions.NotFoundException;
import ar.com.weather_app.exceptions.ValidationException;
import ar.com.weather_app.models.Forecast;
import ar.com.weather_app.repositories.IWeatherRepository;
import ar.com.weather_app.repositories.dao.ForecastDAO;
import ar.com.weather_app.services.IExternalWeatherService;
import ar.com.weather_app.services.IWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherService implements IWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private IExternalWeatherService externalWeatherService;

    private IWeatherRepository repository;

    @Autowired
    public WeatherService(IExternalWeatherService externalWeatherService, IWeatherRepository repository){
        this.externalWeatherService = externalWeatherService;
        this.repository = repository;
    }

    @Override
    public void saveNewClimate(ForecastDAO forecast) {
        repository.save(forecast);
    }

    @Override
    public Forecast getWeather(String lat, String lng) {
        logger.info("<<< getWeather >>>");
        if (!StringUtils.hasText(lat) || !StringUtils.hasText(lng))
            throw new ValidationException("No se envio correctamente la información de posicion", null);
        LocationDTO location = externalWeatherService.getLocation(lat, lng);
        ForecastDAO actualForecast = getLastForecast(location);
        return Forecast.from(actualForecast, location.getLocalizedName());
    }

    private ForecastDAO getLastForecast(LocationDTO location) {
        logger.info("<<< getLastForecast >>>");
        ForecastDAO actualForecast = repository.getForecastByLocationKey(location.getKey(), Date.from(Instant.now().plus(Duration.ofHours(1)).truncatedTo(ChronoUnit.HOURS))).stream().findFirst().orElse(null);
        if (Objects.isNull(actualForecast)) {
        	try {
                List<ForecastDTO> dtos = externalWeatherService.getForecast(location.getKey());
                List<ForecastDAO> daos = repository.saveAll(ForecastDAO.from(dtos, location));
                actualForecast = daos.stream().findFirst().orElseThrow(NotFoundException::new);
        	} catch (RuntimeException exception) {
        		throw new DomainException("No se pudo obtener el pronostico");
        	}

        }
        return actualForecast;
    }

}
