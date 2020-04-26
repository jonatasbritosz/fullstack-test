package teste.java.com.gobots.fullstack.domain;

public enum Category {

	FESTA(""), ROCK(""), POP(""), CLASSICA("");

	private final String playlistId;

	Category(String playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistId() {
		return playlistId;
	}
}
