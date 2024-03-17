package pl.edu.pw.mini.zpoif.project.analysing_operations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;

public abstract class Analyzer {

	protected static final List<AsteroidField> intLongFields = List.of(AsteroidField.id, AsteroidField.neo_reference_id, AsteroidField.epoch_date_close_approach);
	protected static final List<AsteroidField> stringFields = List.of(AsteroidField.name, AsteroidField.nasa_jpl_url, AsteroidField.orbiting_body);
	protected static final List<AsteroidField> doubleFields = List.of(AsteroidField.absolute_magnitude_h, AsteroidField.estimated_diameter_min_km, AsteroidField.estimated_diameter_max_km, AsteroidField.estimated_diameter_min_m, AsteroidField.estimated_diameter_max_m, AsteroidField.estimated_diameter_min_miles, AsteroidField.estimated_diameter_max_miles, AsteroidField.estimated_diameter_min_feet, AsteroidField.estimated_diameter_max_feet, AsteroidField.kilometers_per_second, AsteroidField.kilometers_per_hour, AsteroidField.miles_per_hour, AsteroidField.astronomical_miss_distance, AsteroidField.lunar_miss_distance, AsteroidField.kilometers_miss_distance, AsteroidField.miles_miss_distance);
	protected static final List<AsteroidField> booleanFields = List.of(AsteroidField.is_potentially_hazardous_asteroid, AsteroidField.is_sentry_object);
	protected static final List<AsteroidField> localDateFields = List.of(AsteroidField.close_approach_date);
	protected static final List<AsteroidField> localDateTimeFields = List.of(AsteroidField.close_approach_date_full);
	
	
	
	// dostêp do pól klasy Asteroid
	protected static Function<Asteroid, Long> getIntLongField(AsteroidField field) {
		if (field == AsteroidField.id) {
			return t -> (long) t.getId();
		}
		if (field == AsteroidField.neo_reference_id) {
			return t -> (long) t.getNeo_reference_id();
		}
		if (field == AsteroidField.epoch_date_close_approach) {
			return t -> (long) t.getCloseApproachData().getEpoch_date_close_approach();
		}
		return null;
	}
	
	protected static Function<Asteroid, String> getStringField(AsteroidField field) {
		if (field == AsteroidField.name) {
			return t -> t.getName();
		}
		if (field == AsteroidField.nasa_jpl_url) {
			return t -> t.getNasa_jpl_url();
		}
		if (field == AsteroidField.orbiting_body) {
			return t -> t.getCloseApproachData().getOrbiting_body();
		}
		
		return null;
	}
	
	protected static Function<Asteroid, Double> getDoubleField(AsteroidField field) {
		if (field == AsteroidField.absolute_magnitude_h) {
			return t -> t.getAbsolute_magnitude_h();
		}
		if (field == AsteroidField.estimated_diameter_min_km) {
			return t -> t.getEstimatedDiameterInKilometers().getEstimated_diameter_min();
		}
		if (field == AsteroidField.estimated_diameter_max_km) {
			return t -> t.getEstimatedDiameterInKilometers().getEstimated_diameter_max();
		}
		if (field == AsteroidField.estimated_diameter_min_m) {
			return t -> t.getEstimatedDiameterInMeters().getEstimated_diameter_min();
		}
		if (field == AsteroidField.estimated_diameter_max_m) {
			return t -> t.getEstimatedDiameterInMeters().getEstimated_diameter_max();
		}
		if (field == AsteroidField.estimated_diameter_min_miles) {
			return t -> t.getEstimatedDiameterInMiles().getEstimated_diameter_min();
		}
		if (field == AsteroidField.estimated_diameter_max_miles) {
			return t -> t.getEstimatedDiameterInMiles().getEstimated_diameter_max();
		}
		if (field == AsteroidField.estimated_diameter_min_feet) {
			return t -> t.getEstimatedDiameterInFeet().getEstimated_diameter_min();
		}
		if (field == AsteroidField.estimated_diameter_max_feet) {
			return t -> t.getEstimatedDiameterInFeet().getEstimated_diameter_max();
		}
		
		if (field == AsteroidField.kilometers_per_second) {
			return t -> t.getCloseApproachData().getRelativeVelocity().getKilometers_per_second();
		}
		if (field == AsteroidField.kilometers_per_hour) {
			return t -> t.getCloseApproachData().getRelativeVelocity().getKilometers_per_hour();
		}
		if (field == AsteroidField.miles_per_hour) {
			return t -> t.getCloseApproachData().getRelativeVelocity().getMiles_per_hour();
		}
		
		if (field == AsteroidField.astronomical_miss_distance) {
			return t -> t.getCloseApproachData().getMissDistance().getAstronomical();
		}
		if (field == AsteroidField.lunar_miss_distance) {
			return t -> t.getCloseApproachData().getMissDistance().getLunar();
		}
		if (field == AsteroidField.kilometers_miss_distance) {
			return t -> t.getCloseApproachData().getMissDistance().getKilometers();
		}
		if (field == AsteroidField.miles_miss_distance) {
			return t -> t.getCloseApproachData().getMissDistance().getMiles();
		}
		
		return null;
	}
	
	protected static Function<Asteroid, Boolean> getBooleanField(AsteroidField field) {
		if (field == AsteroidField.is_potentially_hazardous_asteroid) {
			return t -> t.is_potentially_hazardous_asteroid();
		}
		if (field == AsteroidField.is_sentry_object) {
			return t -> t.is_sentry_object();
		}
		
		return null;
	}
	
	protected static Function<Asteroid, LocalDate> getLocalDateField(AsteroidField field) {
		if (field == AsteroidField.close_approach_date) {
			return t -> t.getCloseApproachData().getClose_approach_date();
		}
		
		return null;
	}
	
	protected static Function<Asteroid, LocalDateTime> getLocalDateTimeField(AsteroidField field) {
		if (field == AsteroidField.close_approach_date_full) {
			return t -> t.getCloseApproachData().getClose_approach_date_full();
		}
		
		return null;
	}
}
