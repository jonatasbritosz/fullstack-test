package teste.java.com.gobots.fullstack.validator;

import org.springframework.stereotype.Component;

import teste.java.com.gobots.fullstack.exceptionhandler.BussinesException;

@Component
public class DataEntryValidator {
	
	
	public void validateNameCidade(String cityName) throws BussinesException {
		if (cityName.trim().equals("") || cityName == null) {
			throw new BussinesException("Informe o nome de alguma cidade!");
		}
	}
	
	public void validateLatitudeLongitude(float lat, float lon) throws BussinesException {
		if (lat < -90 || lat > 90) {
			throw new BussinesException("Valor Latitude fora do range permitido minímo: -90 e máximo: 90!");
		}
		
		if (lat < -180 || lat > 180) {
			throw new BussinesException("Valor Longitude fora do range permitido minímo: -180 e máximo: 180!");
		}
	}

}
