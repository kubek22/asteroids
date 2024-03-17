package pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data;

public class RelativeVelocity {

	private double kilometers_per_second;
	private double kilometers_per_hour;
	private double miles_per_hour;
	
	
	public RelativeVelocity(double kilometers_per_second, double kilometers_per_hour, double miles_per_hour) {
		super();
		this.kilometers_per_second = kilometers_per_second;
		this.kilometers_per_hour = kilometers_per_hour;
		this.miles_per_hour = miles_per_hour;
	}


	public double getKilometers_per_second() {
		return kilometers_per_second;
	}


	public double getKilometers_per_hour() {
		return kilometers_per_hour;
	}


	public double getMiles_per_hour() {
		return miles_per_hour;
	}

	
}
