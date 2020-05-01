package teste.java.com.gobots.fullstack.service.decoder;

import org.springframework.beans.factory.annotation.Autowired;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyApiAuthenticationService;

public class SpotifyErrorDecoder implements ErrorDecoder {

	@Autowired
	SpotifyApiAuthenticationService spotifyApiAuthenticationService;

	private final int UNAUTHORIZED = 401;
	
	@Override
	public Exception decode(String methodKey, Response response) {
		
		// remove o token e busca um novo caso o anterior tenha expirado no servidor
		if (response.status() == UNAUTHORIZED) {
			spotifyApiAuthenticationService.removeTokenFromCache();
			throw new RetryableException(UNAUTHORIZED, "Sessão Expirada", null, null, null);
		}
		return new ErrorDecoder.Default().decode(methodKey, response);
	}

}
