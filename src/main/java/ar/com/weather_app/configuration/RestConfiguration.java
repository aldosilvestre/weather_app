package ar.com.weather_app.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

@Configuration
public class RestConfiguration {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public RestTemplate restTemplate(){
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"));
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().removeIf(mc -> mc instanceof MappingJackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

}
