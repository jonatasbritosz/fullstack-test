package teste.java.com.gobots.fullstack.domain;

public enum Category {

	FESTA("37i9dQZF1DX5Ozry5U6G0d"), ROCK("37i9dQZF1DWXRqgorJj26U"), POP("37i9dQZF1DX1ngEVM0lKrb"), CLASSICA("37i9dQZF1DX8qqIDAkKiQg");

	private final String playlistId;

	Category(String playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistId() {
		return playlistId;
	}
}
