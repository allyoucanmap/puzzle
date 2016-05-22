package primitives;

public class Location {
	
	private double lat; 
	private double lon; 
	
	public Location(Double lat, Double lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public Location(String lat, String lon){
		this.lat = Double.parseDouble(lat);
		this.lon = Double.parseDouble(lon);
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

}
