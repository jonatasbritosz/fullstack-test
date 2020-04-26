package teste.java.com.gobots.fullstack.service.spotify;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import feign.RequestTemplate;
import teste.java.com.gobots.fullstack.service.interceptor.RequestInterceptor;

public class SpotifyAuthenticationRequestInterceptor extends RequestInterceptor {

	public static final String AUTHORIZATION_PARAMETER = "Authorization";

	@Value("${services.spotify.token.id}")
	private String clientId;

	@Value("${services.spotify.token.secret}")
	private String clientSecret;

	@Value("${services.spotify.token.path}")
	private String authenticationEndpoint;

	@Override
	public void apply(RequestTemplate template) {

		if (authenticationEndpoint.equals(template.url())) {
			String clientCredentials = clientId + ":" + clientSecret;
			String authorizationValue = "Basic " + new String(Base64.encodeBase64(clientCredentials.getBytes()));

			template.header(AUTHORIZATION_PARAMETER, authorizationValue);
			super.apply(template);
		}
	}
}
