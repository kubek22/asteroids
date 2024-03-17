package pl.edu.pw.mini.zpoif.project.part1;

import pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data.CloseApproachData;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameter;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInFeet;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInKilometers;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInMeters;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInMiles;

public class Asteroid {

	private int id;
	private int neo_reference_id;
	private String name;
	private String nasa_jpl_url;
	private double absolute_magnitude_h;
	
	private EstimatedDiameterInKilometers estimatedDiameterInKilometers;
	private EstimatedDiameterInMeters estimatedDiameterInMeters;
	private EstimatedDiameterInMiles estimatedDiameterInMiles;
	private EstimatedDiameterInFeet estimatedDiameterInFeet;
	
	private boolean is_potentially_hazardous_asteroid;
	private CloseApproachData closeApproachData;
	private boolean is_sentry_object;
	
	
	
	public Asteroid(int id, int neo_reference_id, String name, String nasa_jpl_url, double absolute_magnitude_h,
			EstimatedDiameterInKilometers estimatedDiameterInKilometers,
			EstimatedDiameterInMeters estimatedDiameterInMeters, EstimatedDiameterInMiles estimatedDiameterInMiles,
			EstimatedDiameterInFeet estimatedDiameterInFeet, boolean is_potentially_hazardous_asteroid,
			CloseApproachData closeApproachData, boolean is_sentry_object) {
		super();
		this.id = id;
		this.neo_reference_id = neo_reference_id;
		this.name = name;
		this.nasa_jpl_url = nasa_jpl_url;
		this.absolute_magnitude_h = absolute_magnitude_h;
		this.estimatedDiameterInKilometers = estimatedDiameterInKilometers;
		this.estimatedDiameterInMeters = estimatedDiameterInMeters;
		this.estimatedDiameterInMiles = estimatedDiameterInMiles;
		this.estimatedDiameterInFeet = estimatedDiameterInFeet;
		this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
		this.closeApproachData = closeApproachData;
		this.is_sentry_object = is_sentry_object;
	}



	public int getId() {
		return id;
	}



	public int getNeo_reference_id() {
		return neo_reference_id;
	}



	public String getName() {
		return name;
	}



	public String getNasa_jpl_url() {
		return nasa_jpl_url;
	}



	public double getAbsolute_magnitude_h() {
		return absolute_magnitude_h;
	}



	public EstimatedDiameterInKilometers getEstimatedDiameterInKilometers() {
		return estimatedDiameterInKilometers;
	}



	public EstimatedDiameterInMeters getEstimatedDiameterInMeters() {
		return estimatedDiameterInMeters;
	}



	public EstimatedDiameterInMiles getEstimatedDiameterInMiles() {
		return estimatedDiameterInMiles;
	}



	public EstimatedDiameterInFeet getEstimatedDiameterInFeet() {
		return estimatedDiameterInFeet;
	}



	public boolean is_potentially_hazardous_asteroid() {
		return is_potentially_hazardous_asteroid;
	}



	public CloseApproachData getCloseApproachData() {
		return closeApproachData;
	}



	public boolean is_sentry_object() {
		return is_sentry_object;
	}
	
	

	
	
}
