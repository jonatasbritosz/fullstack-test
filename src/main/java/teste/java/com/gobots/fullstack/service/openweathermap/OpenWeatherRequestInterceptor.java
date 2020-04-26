package teste.java.com.gobots.fullstack.service.openweathermap;


import org.springframework.beans.factory.annotation.Value;

import feign.RequestTemplate;
import teste.java.com.gobots.fullstack.service.interceptor.RequestInterceptor;

public class OpenWeatherRequestInterceptor extends RequestInterceptor {

    @Value("${services.weather.id}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.query("APPID", apiKey);
        super.apply(template);
    }
}
