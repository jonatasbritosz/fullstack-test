package teste.java.com.gobots.fullstack.domain;

import java.util.Objects;

public class City {

	private int id;
	private String name;
	private String state;
	private String country;
	private Coordinate coord;
	
	public City(int id, String name, String state, String country, Coordinate coord) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.country = country;
		this.coord = coord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
}
