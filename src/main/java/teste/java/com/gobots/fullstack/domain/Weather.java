package teste.java.com.gobots.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	
	private int id;
	private String name;
	private int cod;
	private long dt;
	private MainInfo main;
	private Coordinate coord;

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

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public MainInfo getMain() {
		return main;
	}

	public void setMain(MainInfo main) {
		this.main = main;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	@Override
	public String toString() {
		return "Clima{" + "id=" + id + ", nome='" + name + '\'' + ", codigo=" + cod + ", dt=" + dt + ", main=" + main
				+ ", coordenadas=" + coord + '}';
	}

	public class MainInfo {
		private int id;
		private float temp;
		private float pressure;

		@JsonProperty("temp_min")
		private float tempMin;

		@JsonProperty("temp_max")
		private float tempMax;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public float getTemp() {
			return temp;
		}

		public void setTemp(float temp) {
			this.temp = temp;
		}

		public float getPressure() {
			return pressure;
		}

		public void setPressure(float pressure) {
			this.pressure = pressure;
		}

		public float getTempMin() {
			return tempMin;
		}

		public void setTempMin(float tempMin) {
			this.tempMin = tempMin;
		}

		public float getTempMax() {
			return tempMax;
		}

		public void setTempMax(float tempMax) {
			this.tempMax = tempMax;
		}

		@Override
		public String toString() {
			return "MainInfo{" + "id=" + id + ", temp=" + temp + ", pressure=" + pressure + ", tempMin=" + tempMin
					+ ", tempMax=" + tempMax + '}';
		}
	}
}
