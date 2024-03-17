package pl.edu.pw.mini.zpoif.project.part5.filters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pl.edu.pw.mini.zpoif.project.analysing_operations.Analyzer;
import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;
import pl.edu.pw.mini.zpoif.project.part7.sorter.Sorter;

public class Filter extends Analyzer{
	
	
	public static List<Asteroid> rangeFilterBy(List<Asteroid> asteroidsBase, AsteroidField field, String min, String max) throws IncorrectFilterArgumentException{
		// dla pól numerycznych i daty
		try {
			Predicate<Asteroid> predicate = rangeSelect(field, min, max);
			return filter(asteroidsBase, predicate);
		} catch (Exception e) {
			throw new IncorrectFilterArgumentException(); // lub pusta lista bazowa
		}
	}
	
	public static List<Asteroid> filterBy(List<Asteroid> asteroidsBase, AsteroidField field, String value) throws IncorrectFilterArgumentException {
		try {
			Predicate<Asteroid> predicate = select(field, value);
			return filter(asteroidsBase, predicate);
		} catch (Exception e) {
			throw new IncorrectFilterArgumentException(); // lub pusta lista bazowa
		}
	}
	
	private static List<Asteroid> filter(List<Asteroid> asteroidsBase, Predicate<Asteroid> predicate) {
		//return asteroidsBase.stream().filter(predicate).toList();
		return asteroidsBase.stream().filter(predicate).collect(Collectors.toList());
	}
	
	private static Predicate<Asteroid> intLongFilter(AsteroidField field, String value) {
		Function<Asteroid, Long> get = getIntLongField(field);
		long v = Long.valueOf(value);
		return t -> get.apply(t).equals(v);
	}
	
	private static Predicate<Asteroid> intLongRangeFilter(AsteroidField field, String min, String max) {
		Function<Asteroid, Long> get = getIntLongField(field);
		long vMin = Long.valueOf(min);
		long vMax = Long.valueOf(max);
		return t -> get.apply(t) >= vMin && get.apply(t) <= vMax;
	}

	private static Predicate<Asteroid> stringFilter(AsteroidField field, String value) {
		Function<Asteroid, String> get = getStringField(field);
		return t -> get.apply(t).equals(value);
	}
	
	private static Predicate<Asteroid> doubleFilter(AsteroidField field, String value) {
		Function<Asteroid, Double> get = getDoubleField(field);
		double v = Double.valueOf(value);
		return t -> get.apply(t).equals(v);
	}
	
	private static Predicate<Asteroid> doubleRangeFilter(AsteroidField field, String min, String max) {
		Function<Asteroid, Double> get = getDoubleField(field);
		double vMin = Double.valueOf(min);
		double vMax = Double.valueOf(max);
		return t -> get.apply(t) >= vMin && get.apply(t) <= vMax;
	}
	
	private static Predicate<Asteroid> booleanFilter(AsteroidField field, String value) {
		Function<Asteroid, Boolean> get = getBooleanField(field);
		boolean v = Boolean.valueOf(value);
		return t -> get.apply(t).equals(v);
	}
	
	private static Predicate<Asteroid> localDateFilter(AsteroidField field, String value) {
		Function<Asteroid, LocalDate> get = getLocalDateField(field);
		LocalDate v = LocalDate.parse(value);
		return t -> get.apply(t).equals(v);
	}
	
	private static Predicate<Asteroid> localDateRangeFilter(AsteroidField field, String min, String max) {
		Function<Asteroid, LocalDate> get = getLocalDateField(field);
		LocalDate vMin = LocalDate.parse(min);
		LocalDate vMax = LocalDate.parse(max);
		return t -> (get.apply(t).isAfter(vMin) && get.apply(t).isBefore(vMax)) || get.apply(t).equals(vMin) || get.apply(t).equals(vMax);
	}
	
	private static Predicate<Asteroid> localDateTimeFilter(AsteroidField field, String value) {
		Function<Asteroid, LocalDateTime> get = getLocalDateTimeField(field);
		LocalDateTime v = LocalDateTime.parse(value);
		return t -> get.apply(t).equals(v);
	}
	
	private static Predicate<Asteroid> localDateTimeRangeFilter(AsteroidField field, String min, String max) {
		Function<Asteroid, LocalDateTime> get = getLocalDateTimeField(field);
		LocalDateTime vMin = LocalDateTime.parse(min);
		LocalDateTime vMax = LocalDateTime.parse(max);
		return t -> (get.apply(t).isAfter(vMin) && get.apply(t).isBefore(vMax)) || get.apply(t).equals(vMin) || get.apply(t).equals(vMax);
	}
	
	private static Predicate<Asteroid> select(AsteroidField field, String value) {
		//Predicate<Asteroid> predicate = null;
		
		if (intLongFields.contains(field)) {
			return intLongFilter(field, value);
		}
		if (stringFields.contains(field)) {
			return stringFilter(field, value);
		}
		if (doubleFields.contains(field)) {
			return doubleFilter(field, value);
		}
		if (booleanFields.contains(field)) {
			return booleanFilter(field, value);
		}
		if (localDateFields.contains(field)) {
			return localDateFilter(field, value);
		}
		if (localDateTimeFields.contains(field)) {
			return localDateTimeFilter(field, value);
		}
		
		// niepoprawne pole
		return null;
	}
	
	private static Predicate<Asteroid> rangeSelect(AsteroidField field, String min, String max) {
		//Predicate<Asteroid> predicate = null;
		
		if (intLongFields.contains(field)) {
			return intLongRangeFilter(field, min, max);
		}
		if (doubleFields.contains(field)) {
			return doubleRangeFilter(field, min, max);
		}
		if (localDateFields.contains(field)) {
			return localDateRangeFilter(field, min, max);
		}
		if (localDateTimeFields.contains(field)) {
			return localDateTimeRangeFilter(field, min, max);
		}
		
		// niepoprawne pole
		return null;
	}
	
	
	
	public static void main(String[] args) {
		//test
		List<Asteroid> asteroidsBase = new ArrayList<>();
		try {
			filterBy(asteroidsBase, AsteroidField.id, "1s");
		} catch (IncorrectFilterArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("working...");
		
		asteroidsBase.add(new Asteroid(0, 0, null, null, 0, null, null, null, null, false, null, false));
		asteroidsBase.add(new Asteroid(1, 0, null, null, 0, null, null, null, null, false, null, false));
		
		try {
			System.out.println(filterBy(asteroidsBase, AsteroidField.id, "0"));
			System.out.println(filterBy(asteroidsBase, AsteroidField.id, "1"));
			System.out.println(asteroidsBase);
			System.out.println(rangeFilterBy(asteroidsBase, AsteroidField.id, "0", "1"));
		} catch (IncorrectFilterArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Sortowanie");
		System.out.println(asteroidsBase);
		Sorter.sortBy(asteroidsBase, AsteroidField.id, true);
		System.out.println(asteroidsBase);
		Sorter.sortBy(asteroidsBase, AsteroidField.id, false);
		System.out.println(asteroidsBase);
	}
}

