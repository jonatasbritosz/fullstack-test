package teste.java.com.gobots.fullstack.service.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.jackson.JacksonDecoder;

public class ResponseInterceptor extends JacksonDecoder {

    public ResponseInterceptor() {
        super();
    }

    public ResponseInterceptor(ObjectMapper mapper) {
        super(mapper);
    }

}
