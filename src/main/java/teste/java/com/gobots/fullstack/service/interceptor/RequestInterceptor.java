package teste.java.com.gobots.fullstack.service.interceptor;

import com.google.gson.Gson;

import feign.RequestTemplate;

public class RequestInterceptor implements feign.RequestInterceptor {

	private static final Gson GSON = new Gson();

	@Override
	public void apply(RequestTemplate template) {
		if (template.body() != null) {
			GSON.toJson(new String(template.body())).replace("\\n", "").replace("\\\"", "\"");
		}
	}
}
