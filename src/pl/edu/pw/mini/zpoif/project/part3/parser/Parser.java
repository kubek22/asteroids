package pl.edu.pw.mini.zpoif.project.part3.parser;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.print.attribute.standard.DateTimeAtCompleted;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data.CloseApproachData;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data.MissDistance;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data.RelativeVelocity;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInFeet;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInKilometers;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInMeters;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter.EstimatedDiameterInMiles;

public class Parser {

	public static List<Asteroid> parse(String xmlData) throws NoAsteroidsException{
		
		Scanner scanner = new Scanner(xmlData);
		
		// wersja i kodowanie
		// <root>
		skipLines(2, scanner);

		String line = null;
		line = scanner.nextLine();
		if (line.contains("code")) {
			throw new NoAsteroidsException();
		}
		
		
		line = skipUntil("element_count", scanner);
		
		
		int asteroids_number = Integer.valueOf(extractElement(line));
		
		List<Asteroid> asteroids = new ArrayList<>(asteroids_number);
		
		// dojœie do pierwszej asteroidy
		skipUntil("near_earth_objects", scanner);


		for (int i = 0; i < asteroids_number; i++) {
			parseAndAddAsteroid(scanner, asteroids);
		}

		return asteroids;
	}
	
	
	
	private static String extractElement(String line) {
		int start = line.indexOf(">") + 1;
		int end = line.lastIndexOf("<");
		
		return line.substring(start, end);
	}
	
	private static void skipLines(int n, Scanner scanner) {
		for (int i = 0; i < n; i++) {
			scanner.nextLine();
		}
	}
	
	private static String skipUntil(String pattern, Scanner scanner) {
		// zwraca liniê zawieraj¹c¹ dany tekst
		
		String line = scanner.nextLine();
		while (!line.contains(pattern)) {
			line = scanner.nextLine();
		}
		return line;
	}
	
	private static void parseAndAddAsteroid(Scanner scanner, List<Asteroid> asteroids) {
		String line = skipUntil("id", scanner);
		int id = Integer.valueOf(extractElement(line));
		
		line = skipUntil("neo_reference_id", scanner);
		int neo_reference_id = Integer.valueOf(extractElement(line));
		
		line = skipUntil("name", scanner);
		String name = extractElement(line);
		
		line = skipUntil("nasa_jpl_url", scanner);
		String nasa_jpl_url = extractElement(line);
		
		line = skipUntil("absolute_magnitude_h", scanner);
		double absolute_magnitude_h = Double.valueOf(extractElement(line));
		
		
		// EstimatedDiameter
		
		//	km
		line = skipUntil("estimated_diameter_min", scanner);
		double estimated_diameter_min = Double.valueOf(extractElement(line));
		
		line = skipUntil("estimated_diameter_max", scanner);
		double estimated_diameter_max = Double.valueOf(extractElement(line));
		
		EstimatedDiameterInKilometers estimatedDiameterInKilometers = new EstimatedDiameterInKilometers(estimated_diameter_min, estimated_diameter_max);

		// m
		line = skipUntil("estimated_diameter_min", scanner);
		estimated_diameter_min = Double.valueOf(extractElement(line));

		line = skipUntil("estimated_diameter_max", scanner);
		estimated_diameter_max = Double.valueOf(extractElement(line));

		EstimatedDiameterInMeters estimatedDiameterInMeters = new EstimatedDiameterInMeters(estimated_diameter_min, estimated_diameter_max);

		// miles
		line = skipUntil("estimated_diameter_min", scanner);
		estimated_diameter_min = Double.valueOf(extractElement(line));

		line = skipUntil("estimated_diameter_max", scanner);
		estimated_diameter_max = Double.valueOf(extractElement(line));

		EstimatedDiameterInMiles estimatedDiameterInMiles = new EstimatedDiameterInMiles(estimated_diameter_min,
				estimated_diameter_max);

		// feet
		line = skipUntil("estimated_diameter_min", scanner);
		estimated_diameter_min = Double.valueOf(extractElement(line));

		line = skipUntil("estimated_diameter_max", scanner);
		estimated_diameter_max = Double.valueOf(extractElement(line));

		EstimatedDiameterInFeet estimatedDiameterInFeet = new EstimatedDiameterInFeet(estimated_diameter_min, estimated_diameter_max);
		
		//
		
		line = skipUntil("is_potentially_hazardous_asteroid", scanner);
		boolean is_potentially_hazardous_asteroid = Boolean.valueOf(extractElement(line));
		
		
		// CloseApproachData
		
		line = skipUntil("close_approach_date", scanner);
		LocalDate close_approach_date = LocalDate.parse(extractElement(line));
		
		line = skipUntil("close_approach_date_full", scanner);
		LocalDateTime close_approach_date_full = parseDateTime(extractElement(line));
		
		line = skipUntil("epoch_date_close_approach", scanner);
		long epoch_date_close_approach = Long.valueOf(extractElement(line));
		
		// RelativeVelocity
		line = skipUntil("kilometers_per_second", scanner);
		double kilometers_per_second = Double.valueOf(extractElement(line));
		
		line = skipUntil("kilometers_per_hour", scanner);
		double kilometers_per_hour = Double.valueOf(extractElement(line));
		
		line = skipUntil("miles_per_hour", scanner);
		double miles_per_hour = Double.valueOf(extractElement(line));
		
		RelativeVelocity relativeVelocity = new RelativeVelocity(kilometers_per_second, kilometers_per_hour, miles_per_hour);
		//
		
		// MissDistance
		line = skipUntil("astronomical", scanner);
		double astronomical = Double.valueOf(extractElement(line));
		
		line = skipUntil("lunar", scanner);
		double lunar = Double.valueOf(extractElement(line));
		
		line = skipUntil("kilometers", scanner);
		double kilometers = Double.valueOf(extractElement(line));
		
		line = skipUntil("miles", scanner);
		double miles = Double.valueOf(extractElement(line));
		
		MissDistance missDistance = new MissDistance(astronomical, lunar, kilometers, miles);
		//
		
		line = skipUntil("orbiting_body", scanner);
		String orbiting_body = extractElement(line);
		
		CloseApproachData closeApproachData = new CloseApproachData(close_approach_date, close_approach_date_full, epoch_date_close_approach, relativeVelocity, missDistance, orbiting_body);
		//
		
		
		line = skipUntil("is_sentry_object", scanner);
		boolean is_sentry_object = Boolean.valueOf(extractElement(line));
		
		
		Asteroid asteroid = new Asteroid(id, neo_reference_id, name, nasa_jpl_url, absolute_magnitude_h, estimatedDiameterInKilometers, estimatedDiameterInMeters, estimatedDiameterInMiles, estimatedDiameterInFeet, is_potentially_hazardous_asteroid, closeApproachData, is_sentry_object);
		
		asteroids.add(asteroid);
	}
	
	
	private static LocalDateTime parseDateTime(String str_time) {
		String year = str_time.substring(0, 4);
		
		String month = str_time.substring(5, 8);
		
		String day = str_time.substring(9, 11);
		
		String hour = str_time.substring(12, 14);
		
		String minute = str_time.substring(15, 17);
		
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                .withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(month);
		
		LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(year), accessor.get(ChronoField.MONTH_OF_YEAR), Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute));
		
		return localDateTime;
	}
	
}
