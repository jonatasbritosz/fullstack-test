package teste.java.com.gobots.fullstack.service.openweathermap;


import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import teste.java.com.gobots.fullstack.domain.Weather;
import teste.java.com.gobots.fullstack.service.configuration.OpenWeatherServiceConfiguration;
import teste.java.com.gobots.fullstack.util.ApiClient;

@FeignClient(name = "services.weather", url = "${services.weather.uri}", configuration = OpenWeatherServiceConfiguration.class)
public interface OpenWeatherApiClient extends ApiClient.Api {

    @RequestLine("GET ?q={name}&units=metric")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Weather getWeatherInformationByCityName(@Param("name") String cityName);
    
    @RequestLine("GET ?id={id}&units=metric")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Weather getWeatherInformationByCityId(@Param("id") int id);

    @RequestLine("GET ?lat={lat}&lon={lon}&units=metric")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Weather getWeatherInformationNameLongitudeLatitude(@Param("lat") float lat, @Param("lon") float lon);

}
