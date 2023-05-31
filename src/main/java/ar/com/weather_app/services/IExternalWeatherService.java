package ar.com.weather_app.services;

import ar.com.weather_app.dto.ForecastDTO;
import ar.com.weather_app.dto.LocationDTO;
import ar.com.weather_app.dto.WeatherDTO;

import java.util.List;

public interface IExternalWeatherService {

    /** <p>Obtain location key for the location params</p>
     * @param lat String
     * @param lng String
     * @return Long
     */
    public LocationDTO getLocation(String lat, String lng);


    /** <p>Get Forecast of location key</p>
     *
     * @param locationKey Long
     * @return {@link WeatherDTO}
     */
    public List<ForecastDTO> getForecast(Long locationKey);

}
