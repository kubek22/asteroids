package pl.edu.pw.mini.zpoif.project.part1.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part3.converter.Converter;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;
import pl.edu.pw.mini.zpoif.project.part3.parser.Parser;

public class Connection {
	public static void main(String[] args) throws IOException, NoAsteroidsException {
//		URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=DEMO_KEY");
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");
//		BufferedReader in = new BufferedReader(
//				  new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer content = new StringBuffer();
//		while ((inputLine = in.readLine()) != null) {
//			content.append(inputLine);
//		}
//		in.close();
//		//System.out.print(content);
//		con.disconnect();		
//	
//		ObjectMapper mapper = new ObjectMapper();
//		//Root root = mapper.readValue(content.toString(), Root.class); 
		
		String xml = null;
		try {
			xml = downloadXMLData("2017-09-07", "2017-09-08");
			
			System.out.println(xml);
			
			List<Asteroid> asteroids = Parser.parse(xml);
			
			for (Asteroid asteroid : asteroids) {
				System.out.println(asteroid);
			}
			
		} catch (IncorrectDateException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static String downloadXMLData(String startDate, String end_date) throws IOException, IncorrectDateException {
		//https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
		
		// format daty: YYYY-MM-DD
		
		// moj api key nBqdJtULOwuBwhBSIZ0FiazfDLaGSqFvp07jAElQ
		
		String matApiKey = "nBqdJtULOwuBwhBSIZ0FiazfDLaGSqFvp07jAElQ";
		
		
		checkDate(startDate, end_date);
		
		String addressStart = "https://api.nasa.gov/neo/rest/v1/feed?start_date=";
		String addressMid = "&end_date=";
		String addressEnd = "&api_key="+matApiKey;
		
		String address = addressStart +  startDate + addressMid + end_date + addressEnd;
		
		//System.out.println(address);
		
		String jsonData = downloadData(address);
		
		String xmlData = Converter.convertJSONToXML(jsonData);
		
		return xmlData;
	}
	
	
	private static String downloadData(String address) throws IOException {
		URL url = new URL(address);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		return content.toString();
	}
	
	private static void checkDate(String startDate, String end_date) throws IncorrectDateException {
		final int len = 10;
		if (startDate.length() != len || end_date.length() != len) {
			throw new IncorrectDateException();
		}
		
		final String delimiter = "-";
		if (!startDate.substring(4, 5).equals(delimiter) || !end_date.substring(4, 5).equals(delimiter) || 
				!startDate.substring(7, 8).equals(delimiter) || !end_date.substring(7, 8).equals(delimiter)) {
			throw new IncorrectDateException();
		}
		
		try {
			LocalDate.parse(startDate);
		} catch (DateTimeParseException e) {
			throw new IncorrectDateException();
		}
	}
	
}
