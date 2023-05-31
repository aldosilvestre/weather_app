package ar.com.weather_app.controllers;

import ar.com.weather_app.models.Forecast;
import ar.com.weather_app.services.IWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** <h2>Weather Controller</h2>
 * <p>This controller handler data from weather operations</p>
 * @Autor Aldo Silvestre
 * @Since 1.0.0
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final IWeatherService weatherService;

    public WeatherController(@Autowired IWeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forecast> getActualForecast(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude){
        logger.info("<<< getActualForecast >>>");
        return ResponseEntity.ok(weatherService.getWeather(latitude, longitude));
    }

}
