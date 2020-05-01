package teste.java.com.gobots.fullstack.service.decoder;

import static feign.FeignException.errorStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class OpenWeatherErrorDecoder implements ErrorDecoder {
	
	// por default o Feign apenas lança FeignException, por 
	// meio do ErrorDecoder podemos tratar as exceções especificamente.
	
	@Override
	public Exception decode(String methodKey, Response response) {

		HttpStatus httpStatus = HttpStatus.valueOf(response.status());

		if (response.status() >= 400 && response.status() <= 499) {
			return new HttpClientErrorException(httpStatus, response.reason());
		}
		if (response.status() >= 500 && response.status() <= 599) {
			return new HttpServerErrorException(httpStatus, response.reason());
		}
		return errorStatus(methodKey, response);
	}

}
