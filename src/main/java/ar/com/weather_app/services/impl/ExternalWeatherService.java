package ar.com.weather_app.services.impl;

import ar.com.weather_app.dto.ForecastDTO;
import ar.com.weather_app.dto.LocationDTO;
import ar.com.weather_app.exceptions.DomainException;
import ar.com.weather_app.services.IExternalWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExternalWeatherService implements IExternalWeatherService {

    private final static Logger logger = LoggerFactory.getLogger(ExternalWeatherService.class);

    @Value("${api.weather.apikey}")
    private String apiKey;

    @Value("${api.weather.host}")
    private String host;

    @Value("${api.weather.range}")
    private Integer rangeHours;

    @Value("${api.weather.lang}")
    private String lang;

    @Value("${api.weather.metric}")
    private String metric;

    private final RestTemplate rest = new RestTemplate();

    @Override
    public LocationDTO getLocation(String lat, String lng) {
        logger.info("<<< getLocationKey >>>");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(host + "/locations/v1/cities/geoposition/search");
        builder.queryParam("q",  String.format("%s,%s", lat, lng) );
        builder.queryParam("apikey", apiKey);
        LocationDTO result = rest.getForEntity(builder.encode().build().toUri(), LocationDTO.class).getBody();
        if (result == null) throw new DomainException("No existen datos de la ubicacion agregada");
        return result;
    }

    @Override
    public List<ForecastDTO> getForecast(Long locationKey) {
        logger.info("<<< getForecast >>>");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(host + "/forecasts/v1/hourly/"+ rangeHours +"hour/" + locationKey);
        builder.queryParam("apikey", apiKey);
        builder.queryParam("language", lang);
        builder.queryParam("metric", metric);
        URI uri = builder.encode().build().toUri();
        return Arrays.stream(Objects.requireNonNull(rest.getForEntity(uri, ForecastDTO[].class).getBody())).collect(Collectors.toList());
    }
}
