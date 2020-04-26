package teste.java.com.gobots.fullstack.service.spotify;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.jackson.JacksonDecoder;

public class SpotifyResponse extends JacksonDecoder {

    public SpotifyResponse() {
        super();
    }

    public SpotifyResponse(ObjectMapper mapper) {
        super(mapper);
    }

}
