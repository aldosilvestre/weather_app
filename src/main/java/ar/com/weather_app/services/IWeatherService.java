package ar.com.weather_app.services;

import ar.com.weather_app.models.Forecast;
import ar.com.weather_app.repositories.dao.ForecastDAO;

public interface IWeatherService {

    /** <p>Save new climate request </p>
     * @param forecast {@link ForecastDAO}
     */
    public void saveNewClimate(ForecastDAO forecast);

    /** <p>Get last weather data from external service or database</p>
     *
     * @return {@link Forecast}
     */
    public Forecast getWeather(String latitude, String longitude);
}
