package teste.java.com.gobots.fullstack.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import teste.java.com.gobots.fullstack.domain.City;

@Service
public class FakeCitiesRepository {

	private static String fileName = "classpath:city.list.json";

	private Collection<City> cidades;
	
	@Autowired
    ResourceLoader resourceLoader;

	public City getCityByName(String cityName) {
		getCitysListOpenWeather();
		return cidades.stream().filter(c -> c.getName().equalsIgnoreCase(cityName)).findFirst().orElse(null);
	}

	public Collection<City> getCitysListOpenWeather() {
		cidades = new LinkedHashSet<City>();
		try {
			
			Resource resource = resourceLoader.getResource(fileName);
			InputStream stream = resource.getInputStream();
			JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
			Gson gson = new Gson();

			// Read file in stream mode
			reader.beginArray();
			while (reader.hasNext()) {
				City cidade = gson.fromJson(reader, City.class);
				cidades.add(cidade);
			}
			reader.endArray();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cidades;
	}

	public Collection<City> getCidades() {
		return cidades;
	}

	public void setCidades(Collection<City> cidades) {
		this.cidades = cidades;
	}
	
}
