package unit.services;

import ar.com.weather_app.dto.LocationDTO;
import ar.com.weather_app.exceptions.DomainException;
import ar.com.weather_app.exceptions.ValidationException;
import ar.com.weather_app.models.Forecast;
import ar.com.weather_app.repositories.IWeatherRepository;
import ar.com.weather_app.repositories.dao.ForecastDAO;
import ar.com.weather_app.services.IExternalWeatherService;
import ar.com.weather_app.services.impl.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherService.class)
public class WeatherServiceTest {

    private WeatherService weatherService;

    @MockBean
    private IExternalWeatherService externalWeatherService;
    @MockBean
    private IWeatherRepository repository;

    private ArrayList<ForecastDAO> listForecastDao;

    private LocationDTO locationDTO;

    @BeforeEach
    public void setUp() {
        listForecastDao = new ArrayList<>();
        ForecastDAO forecastDAO = new ForecastDAO();
        forecastDAO.setId(1L);
        forecastDAO.setLocationKey(123L);
        forecastDAO.setTemperature(15f);
        forecastDAO.setDescription("description");
        listForecastDao.add(forecastDAO);

        locationDTO = new LocationDTO();
        locationDTO.setKey(12345L);
        locationDTO.setLocalizedName("Argentina/Salta");

        weatherService = new WeatherService(externalWeatherService, repository);
    }


    @Test
    public void getWeatherSuccessfully() {
        when(externalWeatherService.getLocation(any(), any())).thenReturn(locationDTO);
        when(repository.getForecastByLocationKey(any(), any())).thenReturn(listForecastDao);

        Forecast forecast = weatherService.getWeather("123", "456");

        assertThat(forecast.getDescription().equals("description"));
        assertThat(forecast.getTemperature().equals(15f));
    }


    @Test
    public void getWeatherWithoutLatitudeOrLongitude() {
    	assertThrows(ValidationException.class, () -> {
    		weatherService.getWeather("123", null);
    	});
    }

    @Test
    public void getWeatherFailWhenExternalServiceFail() {
        when(externalWeatherService.getLocation(any(), any())).thenReturn(locationDTO);
        when(repository.getForecastByLocationKey(any(), any())).thenReturn(Collections.emptyList());
        when(externalWeatherService.getForecast(any())).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        
        assertThrows(DomainException.class, () -> {
        	weatherService.getWeather("123","456");
        });

    }

}
