package teste.java.com.gobots.fullstack.service.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.Contract;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import teste.java.com.gobots.fullstack.service.decoder.SpotifyErrorDecoder;
import teste.java.com.gobots.fullstack.service.interceptor.ResponseInterceptor;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyAuthClient;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyAuthenticationRequestInterceptor;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyPlaylistApiClient;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyRequestInterceptor;
import teste.java.com.gobots.fullstack.util.ApiClient;

@Configuration
public class SpotifyServiceConfiguration {

	@Value("${services.spotify.token.uri}")
	private String tokenUri;

	@Value("${services.spotify.token.path}")
	private String tokenPath;

	@Value("${services.spotify.uri}")
	private String apiUri;

	@Value("${services.spotify.token.id}")
	private String clientId;

	@Value("${services.spotify.token.secret}")
	private String clientSecret;

	@Value("${services.spotify.timeout}")
	private int connectTimeout;

	@Value("${services.spotify.timeout}")
	private int readTimeout;

	@Bean
	public SpotifyAuthClient createAuthenticateApi() {
		return buildAuthenticationApiClient().buildClient(SpotifyAuthClient.class);
	}

	@Bean
	public SpotifyPlaylistApiClient createPlaylistApi() {
		return buildBaseApiClient().setBasePath(apiUri).buildClient(SpotifyPlaylistApiClient.class);
	}

	@Bean
	public RequestInterceptor getRequestInterceptor() {
		return new SpotifyRequestInterceptor();
	}

	@Bean
	public RequestInterceptor getAuthRequestInterceptor() {
		return new SpotifyAuthenticationRequestInterceptor();
	}

	@Bean
	public Decoder getDecoder() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return new ResponseInterceptor(objectMapper);
	}

	@Bean
	public ErrorDecoder createSpotifyErrorDecoder() {
		return new SpotifyErrorDecoder();
	}

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}

	private ApiClient buildBaseApiClient() {
		ApiClient apiClient = new ApiClient();
		apiClient.getFeignBuilder()
				.options(new Request.Options(connectTimeout, TimeUnit.SECONDS, readTimeout, TimeUnit.SECONDS, true));
		apiClient.getFeignBuilder().requestInterceptor(getRequestInterceptor());
		return apiClient;
	}

	private ApiClient buildAuthenticationApiClient() {
		final ApiClient apiClient = buildBaseApiClient();
		apiClient.setBasePath(tokenUri);
		apiClient.getFeignBuilder().requestInterceptor(getAuthRequestInterceptor());
		apiClient.getFeignBuilder().encoder(getEncoder());
		apiClient.getFeignBuilder().decoder(getDecoder());
		return apiClient;
	}

	@Bean
	public FormEncoder getEncoder() {
		FormEncoder encoder = new FormEncoder();
		encoder.getContentProcessor(ContentType.URLENCODED);
		return encoder;
	}
}
