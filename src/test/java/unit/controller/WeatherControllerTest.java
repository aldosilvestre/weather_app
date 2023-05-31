package unit.controller;

import ar.com.weather_app.controllers.WeatherController;
import ar.com.weather_app.exceptions.HandlerException;
import ar.com.weather_app.exceptions.ValidationException;
import ar.com.weather_app.models.Forecast;
import ar.com.weather_app.services.IWeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureJsonTesters
@SpringBootTest(classes = WeatherController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IWeatherService weatherService;

    @Autowired
    private JacksonTester<Forecast> jacksonConverter;

    private final Forecast forecast = new Forecast();

    @Test
    public void getForecast() throws Exception{
        forecast.setPrecipitation(7);
        forecast.setTemperature(21.5f);
        forecast.setMetric("C");
        forecast.setLocation("Argentina");
        forecast.setDescription("Soleado");

        String url = "/weather/get?lat=123&lng=456";

        when( weatherService.getWeather(any(), any()) ).thenReturn( forecast );

         MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(result.getContentAsString()).isEqualTo(jacksonConverter.write(forecast).getJson());
    }


    @InjectMocks
    private HandlerException exceptionHandler;

    @Test
    public void convertExceptionHandlerWorking() {

        ResponseEntity<String> response =  exceptionHandler.handleValidationException( new ValidationException("error", null));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThat(response.getBody().equals("error"));
    }

}