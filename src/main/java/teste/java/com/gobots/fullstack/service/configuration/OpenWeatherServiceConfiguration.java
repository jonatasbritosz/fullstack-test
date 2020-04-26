package teste.java.com.gobots.fullstack.service.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Request;
import feign.RequestInterceptor;
import teste.java.com.gobots.fullstack.service.openweathermap.OpenWeatherApiClient;
import teste.java.com.gobots.fullstack.service.openweathermap.OpenWeatherRequestInterceptor;
import teste.java.com.gobots.fullstack.util.ApiClient;

@Configuration
public class OpenWeatherServiceConfiguration {

    @Value("${services.weather.uri}")
    private String apiUri;

    @Value("${services.weather.timeout}")
    private int openWeatherTimeout;

    @Bean
    public OpenWeatherApiClient createApi() {
        return buildBaseApiClient()
                .setBasePath(apiUri)
                .buildClient(OpenWeatherApiClient.class);
    }

	/*
	 * @Bean public ErrorDecoder createOpenWeatherErrorDecoder() { return new
	 * OpenWeatherErrorDecoder(); }
	 */

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new OpenWeatherRequestInterceptor();
    }

    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }

    private ApiClient buildBaseApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.getFeignBuilder().options(new Request.Options(openWeatherTimeout, openWeatherTimeout));
        apiClient.getFeignBuilder().requestInterceptor(getRequestInterceptor());
        return apiClient;
    }
}
