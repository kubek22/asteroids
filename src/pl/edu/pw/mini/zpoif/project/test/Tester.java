package pl.edu.pw.mini.zpoif.project.test;

import java.io.IOException;
import java.util.List;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;
import pl.edu.pw.mini.zpoif.project.part1.connection.Connection;
import pl.edu.pw.mini.zpoif.project.part1.connection.IncorrectDateException;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;
import pl.edu.pw.mini.zpoif.project.part3.parser.Parser;
import pl.edu.pw.mini.zpoif.project.part5.filters.Filter;
import pl.edu.pw.mini.zpoif.project.part5.filters.IncorrectFilterArgumentException;
import pl.edu.pw.mini.zpoif.project.part7.sorter.Sorter;

public class Tester {

	public static void main(String[] args) {
		try {
			// wczytanie danych
			// dostêpny zakres to max 7 dni
			String xml = Connection.downloadXMLData("2021-09-07", "2021-09-10");
			
			try {
				// konwersja na obiekty
				List<Asteroid> asteroids = Parser.parse(xml);
				List<Asteroid> working = null;
				
				try {
					//filtrowanie
					working = Filter.rangeFilterBy(asteroids, AsteroidField.neo_reference_id, "24278000", "54279999");
					working.stream().forEachOrdered(t -> System.out.println(t.getNeo_reference_id()));
					
					System.out.println();
					working = Filter.rangeFilterBy(asteroids, AsteroidField.close_approach_date, "2017-09-07", "2017-09-07");
					working.stream().forEachOrdered(t -> System.out.println(t.getCloseApproachData().getClose_approach_date()));
					
					System.out.println();
					//sortowanie
					Sorter.sortBy(asteroids, AsteroidField.absolute_magnitude_h, true);
					asteroids.stream().forEachOrdered(t -> System.out.println(t.getAbsolute_magnitude_h()));
					
					System.out.println();
					Sorter.sortBy(asteroids, AsteroidField.close_approach_date_full, false);
					asteroids.stream().forEachOrdered(t -> System.out.println(t.getCloseApproachData().getClose_approach_date_full()));
					
				} catch (IncorrectFilterArgumentException e) {
					System.out.println("B³¹d filtrowania");
				}
			} catch (NoAsteroidsException e) {
				System.out.println("B³¹d przy konwertowaniu");
			}
			
		} catch (IOException | IncorrectDateException e) {
			System.out.println("B³¹d pobierania danych");
		}
	}
}
