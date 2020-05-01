package teste.java.com.gobots.fullstack.exceptionhandler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import feign.RetryableException;

@ControllerAdvice
public class PlaylistApiExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> defaultErrorHandler(Exception e) {
		Set<String> messages = new HashSet<>();
		messages.add("Erro na consulta ao servidor.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new BussinesExceptionError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), messages));
	}
	
	@ExceptionHandler(value = RetryableException.class)
	public ResponseEntity<Object> handleRetryableException(RetryableException e) {
		Set<String> messages = new HashSet<>();
		messages.add("Serviço Indisponível no Momento.");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body(new BussinesExceptionError(HttpStatus.SERVICE_UNAVAILABLE.value(),
						HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(), e.getMessage(), messages));
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
		Set<String> messages = new HashSet<>();
		String msg = String.format("Tipo entrada errado. Parâmetro '%s', Valor: '%s', Tipo Esperado: %s", e.getName(),
				e.getValue(), e.getRequiredType());
		messages.add(msg);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BussinesExceptionError(
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMostSpecificCause().toString(), messages));
	}

	@ExceptionHandler(value = BussinesException.class)
	public ResponseEntity<Object> handleBussinesException(BussinesException e) {
		Set<String> messages = new HashSet<>();
		messages.add(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BussinesExceptionError(
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(), messages));
	}

	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<Object> handleClientException(HttpClientErrorException e) {
		Set<String> messages = new HashSet<>();
		if (e.getStatusCode().value() == 404) {
			messages.add("Playlist não encontrada.");
		} else {
			messages.add(e.getMessage());
		}
		return ResponseEntity.status(e.getStatusCode()).body(
				new BussinesExceptionError(e.getStatusCode().value(), e.getStatusCode().getReasonPhrase(), e.getLocalizedMessage(), messages));
	}

	@ExceptionHandler(value = HttpServerErrorException.class)
	public ResponseEntity<Object> handleServerException(HttpServerErrorException e) {
		Set<String> messages = new HashSet<>();
		messages.add(e.getMessage());
		return ResponseEntity.status(e.getStatusCode()).body(
				new BussinesExceptionError(e.getStatusCode().value(), e.getStatusCode().getReasonPhrase(), e.getMessage(), messages));
	}

}
