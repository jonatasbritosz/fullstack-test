package teste.java.com.gobots.fullstack.service.spotify;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import teste.java.com.gobots.fullstack.domain.PlaylistRecuperada;
import teste.java.com.gobots.fullstack.service.configuration.SpotifyServiceConfiguration;
import teste.java.com.gobots.fullstack.util.ApiClient;

@FunctionalInterface
@FeignClient(name = "services.spotify", url = "${services.spotify.uri}", configuration = SpotifyServiceConfiguration.class)
public interface SpotifyPlaylistApiClient extends ApiClient.Api {

	@RequestLine("GET /v1/playlists/{playlistId}/tracks?fields=items(track(name))&limit_param=10")
	@Headers({ "Accept: */*", "Cache-Control: no-cache", "Content-Type: application/json" })
	PlaylistRecuperada getPlaylist(@Param("playlistId") String playlistId);

}
