package teste.java.com.gobots.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import teste.java.com.gobots.fullstack.domain.PlaylistRecuperada;
import teste.java.com.gobots.fullstack.exceptionhandler.BussinesException;
import teste.java.com.gobots.fullstack.service.PlaylistService;
import teste.java.com.gobots.fullstack.validator.DataEntryValidator;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	DataEntryValidator dataEntryValidator;
	
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" }, params = { "city" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PlaylistRecuperada searchByCityName(@RequestParam(value = "city") String cityName) throws BussinesException {
		
		dataEntryValidator.validateNameCidade(cityName);
		PlaylistRecuperada playlist = playlistService.searchPlaylist(cityName);
		return playlist;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" }, params = { "lat", "lon" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PlaylistRecuperada searchByCoord(@RequestParam(value = "lat") float lat,
			@RequestParam(value = "lon") float lon) throws BussinesException, NumberFormatException {

		dataEntryValidator.validateLatitudeLongitude(lat, lon);
		
		PlaylistRecuperada playlist = playlistService.searchPlaylist(lat, lon);
		return playlist;
	}

}
