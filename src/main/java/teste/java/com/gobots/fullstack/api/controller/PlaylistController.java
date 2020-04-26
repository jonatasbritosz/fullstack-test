package teste.java.com.gobots.fullstack.api.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import teste.java.com.gobots.fullstack.domain.PlaylistRecuperada;
import teste.java.com.gobots.fullstack.exceptionhandler.NotFoundException;
import teste.java.com.gobots.fullstack.service.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" }, params = { "city" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PlaylistRecuperada searchByCityName(@Valid @RequestParam(value = "city") String cityName)
			throws NotFoundException {
		PlaylistRecuperada playlist = playlistService.searchPlaylist(cityName);

		if (playlist == null || ArrayUtils.isEmpty(playlist.getItensPlaylist())) {
			throw new NotFoundException("No playlist found");
		}
		return playlist;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" }, params = { "lat", "lon" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PlaylistRecuperada searchByCoord(@RequestParam(value = "lat") float lat,
			@RequestParam(value = "lon") float lon) throws NotFoundException {

		PlaylistRecuperada playlist = playlistService.searchPlaylist(lat, lon);
		if (playlist == null || ArrayUtils.isEmpty(playlist.getItensPlaylist())) {
			throw new NotFoundException("No playlist found");
		}

		return playlist;
	}

}
