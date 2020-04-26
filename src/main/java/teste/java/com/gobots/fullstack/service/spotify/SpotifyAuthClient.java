package teste.java.com.gobots.fullstack.service.spotify;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import teste.java.com.gobots.fullstack.service.configuration.SpotifyServiceConfiguration;
import teste.java.com.gobots.fullstack.util.ApiClient;

@FunctionalInterface
@FeignClient(name = "services.spotify", url = "${services.spotify.token.uri}", configuration = SpotifyServiceConfiguration.class)
public interface SpotifyAuthClient extends ApiClient.Api {

    @RequestLine("POST /api/token")
    @Headers({"Accept: */*", "Cache-Control: no-cache", "Content-Type: application/x-www-form-urlencoded"})
    Map<String, String> authorizeUsingPOST(@Param("grant_type") String grantType);

}
