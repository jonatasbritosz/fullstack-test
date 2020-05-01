package teste.java.com.gobots.fullstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teste.java.com.gobots.fullstack.domain.Category;
import teste.java.com.gobots.fullstack.domain.City;
import teste.java.com.gobots.fullstack.domain.PlaylistRecuperada;
import teste.java.com.gobots.fullstack.domain.Weather;
import teste.java.com.gobots.fullstack.service.openweathermap.OpenWeatherApiClient;
import teste.java.com.gobots.fullstack.service.spotify.SpotifyPlaylistApiClient;
import teste.java.com.gobots.fullstack.util.CitiesRepository;

@Component
public class PlaylistService {

	@Autowired
	SpotifyPlaylistApiClient spotifyPlaylistApiClient;

	@Autowired
	OpenWeatherApiClient openWeatherApiClient;
	
	@Autowired
	CitiesRepository citiesRepository;

	public PlaylistRecuperada searchPlaylist(String cityName) {
		
		City city = citiesRepository.getCityByName(cityName);
		Weather weather = new Weather();
		if(city != null) {
			weather = openWeatherApiClient.getWeatherInformationByCityId(city.getId());
		}else {
			weather = openWeatherApiClient.getWeatherInformationByCityName(cityName);
		}
		return findPlaylistByWeather(weather);
	}

	public PlaylistRecuperada searchPlaylist(float lat, float lon) {
		Weather weather = openWeatherApiClient.getWeatherInformationNameLongitudeLatitude(lat, lon);
		return findPlaylistByWeather(weather);
	}

	public PlaylistRecuperada findPlaylistByWeather(Weather weather) {
		Category category;
		float temp = weather.getMain().getTemp();

		if (temp >= 30) {
			category = Category.FESTA;
		} else if (temp >= 15 && temp < 30) {
			category = Category.POP;
		} else if (temp >= 10 && temp < 15) {
			category = Category.ROCK;
		} else {
			category = Category.CLASSICA;
		}
		return spotifyPlaylistApiClient.getPlaylist(category.getPlaylistId());
	}
}