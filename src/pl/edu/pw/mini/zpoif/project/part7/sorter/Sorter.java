package pl.edu.pw.mini.zpoif.project.part7.sorter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import pl.edu.pw.mini.zpoif.project.analysing_operations.Analyzer;
import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;

public class Sorter extends Analyzer{

	public static void sortBy(List<Asteroid> asteroidsBase, AsteroidField field, boolean ascending) {
		// sortuje w miejcu
		
		Collections.sort(asteroidsBase, select(field, ascending));
		
		
		
//		Integer.valueOf(1).compareTo(2);// * (ascending ? 1 : -1);
//		Long.valueOf(1).compareTo((long) 2);
//		Double.valueOf(0).compareTo(1.1);
//		Boolean.valueOf(false).compareTo(true);
//		LocalDate.ofYearDay(0, 0).compareTo(LocalDate.parse("sad"));
//		LocalDateTime.parse("123").compareTo(LocalDateTime.parse("21312"));
		
		// sprawdziæ typ, ¿eby skastowaæ do obiektu, w przypadku typów prostych (czyli wszystko oprócz daty)
		// sprawdziæ pole, ¿eby siê do niego dostaæ
	}
	

	private static Comparator<Asteroid> select(AsteroidField field, boolean ascending) {
		int condition = ascending ? 1 : -1;
		
		
		if (Analyzer.intLongFields.contains(field)) {
			Function<Asteroid, Long> get = getIntLongField(field);
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		if (Analyzer.stringFields.contains(field)) {
			Function<Asteroid, String> get = getStringField(field);
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		if (Analyzer.doubleFields.contains(field)) {
			
			
			Function<Asteroid, Double> get = getDoubleField(field);
			
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		if (Analyzer.booleanFields.contains(field)) {
			Function<Asteroid, Boolean> get = getBooleanField(field);
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		if (Analyzer.localDateFields.contains(field)) {
			Function<Asteroid, LocalDate> get = getLocalDateField(field);
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		if (Analyzer.localDateTimeFields.contains(field)) {
			Function<Asteroid, LocalDateTime> get = getLocalDateTimeField(field);
			return (o1, o2) -> get.apply(o1).compareTo(get.apply(o2)) * condition;
		}
		
		// niepoprawne pole
		return null;
	} 
	
}
