package pl.edu.pw.mini.zpoif.project.part1;

public enum AsteroidField {
	
	//int, long
	id, neo_reference_id, 
	epoch_date_close_approach,
	
	//string
	name, nasa_jpl_url, 
	orbiting_body,
	
	//double
	absolute_magnitude_h, 
	estimated_diameter_min_km, estimated_diameter_max_km,
	estimated_diameter_min_m, estimated_diameter_max_m,
	estimated_diameter_min_miles, estimated_diameter_max_miles,
	estimated_diameter_min_feet, estimated_diameter_max_feet,
	kilometers_per_second, kilometers_per_hour, miles_per_hour,
	astronomical_miss_distance, lunar_miss_distance, kilometers_miss_distance, miles_miss_distance,
	
	//boolean
	is_potentially_hazardous_asteroid, is_sentry_object, 
	//LocalDate
	close_approach_date,
	
	//LocalDateTime
	close_approach_date_full
	
	
	;
}
