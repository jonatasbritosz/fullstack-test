package teste.java.com.gobots.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistRecuperada {

	@JsonProperty("items")
	private ItemPlaylist[] itensPlaylist;

	public ItemPlaylist[] getItensPlaylist() {
		return itensPlaylist;
	}

	public void setItensPlaylist(ItemPlaylist[] itensPlaylist) {
		this.itensPlaylist = itensPlaylist;
	}

}
