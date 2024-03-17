package pl.edu.pw.mini.zpoif.project.part8.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data.CloseApproachData;

public class AsteroidInformation extends JPanel{
	
	
	String[][] asteroidTable = new String[20][2];
	
	
	
	public JPanel getAsteroidMainPanel(MainFrame mainFrame) {
			
			JPanel jPanel = new JPanel();
			
			jPanel.setLayout(new BorderLayout());
			
			createTable();
			
			
			String[] asteroidColumns = {"Variables", "Value"};
			
			JTable jTableAsteroid = new JTable(asteroidTable, asteroidColumns);
			
			jTableAsteroid.setEnabled(false);
			
	
			jTableAsteroid.getColumnModel().getColumn(0).setPreferredWidth(200);
			jTableAsteroid.getColumnModel().getColumn(1).setPreferredWidth(200);
			
			

			jPanel.add(jTableAsteroid.getTableHeader(), BorderLayout.NORTH);
			jPanel.add(jTableAsteroid,BorderLayout.CENTER);
			
			
			return jPanel;
		}
	
	private JLabel getTextPanel(Color color, int size, String text) {
			
			JLabel jlabel = new JLabel();
			
			jlabel.setText(text);
			
			jlabel.setForeground(color);
			
			jlabel.setFont(new Font("Arial",Font.PLAIN, size));         
			
			return jlabel;
			
		}
	
	public void createTable() {
			//tabela informacji
			
			
			//dane asteroidy
			asteroidTable[0][0] = "Name";
			asteroidTable[1][0] = "Id";
			asteroidTable[2][0] = "Neo Reference Id";
			asteroidTable[3][0] = "Nasa JPL URL";
			asteroidTable[4][0] = "Is potentially hazardous";
			asteroidTable[5][0] = "Is sentry object";
			asteroidTable[6][0] = "Absolute Magnitude H";
			asteroidTable[7][0] = "Min Estimated Diameter in meters";
			asteroidTable[8][0] = "Max Estimated Diameter in meters";
			
			//dane  closeapproachdata
			asteroidTable[9][0] = "Close Approach Date";
			asteroidTable[10][0] = "Close Approach Date Full";
			asteroidTable[11][0] = "Epoch Date";
			asteroidTable[12][0] = "Relative Velocity km/h";
			asteroidTable[13][0] = "Relative Velocity km/s";
			asteroidTable[14][0] = "Relative Velocity mph";
			asteroidTable[15][0] = "Miss Distance in astronimical";
			asteroidTable[16][0] = "Miss Distance in lunar";
			asteroidTable[17][0] = "Miss Distance in kilometers";
			asteroidTable[18][0] = "Miss Distance in miles";
			asteroidTable[19][0] = "Orbiting Body";
	}
	
	
	public void updateTables(Asteroid ast) {
			asteroidTable[0][1] = ast.getName();
			asteroidTable[1][1] = String.valueOf(ast.getId());
			asteroidTable[2][1] = String.valueOf(ast.getNeo_reference_id());
			asteroidTable[3][1] = ast.getNasa_jpl_url();
			asteroidTable[4][1] = String.valueOf(ast.is_potentially_hazardous_asteroid());
			asteroidTable[5][1] = String.valueOf(ast.is_sentry_object());
			asteroidTable[6][1] = String.valueOf(ast.getAbsolute_magnitude_h());
			asteroidTable[7][1] = String.valueOf(ast.getEstimatedDiameterInMeters().getEstimated_diameter_min());
			asteroidTable[8][1] = String.valueOf(ast.getEstimatedDiameterInMeters().getEstimated_diameter_max());
			
			
			
			CloseApproachData data = ast.getCloseApproachData();
			
			asteroidTable[9][1] = data.getClose_approach_date().toString();
			asteroidTable[10][1] = data.getClose_approach_date_full().toString();
			asteroidTable[11][1] = String.valueOf(data.getEpoch_date_close_approach());
			asteroidTable[12][1] = String.valueOf(data.getRelativeVelocity().getKilometers_per_hour());
			asteroidTable[13][1] = String.valueOf(data.getRelativeVelocity().getKilometers_per_second());
			asteroidTable[14][1] = String.valueOf(data.getRelativeVelocity().getMiles_per_hour());
			asteroidTable[15][1] = String.valueOf(data.getMissDistance().getAstronomical());
			asteroidTable[16][1] = String.valueOf(data.getMissDistance().getLunar());
			asteroidTable[17][1] = String.valueOf(data.getMissDistance().getKilometers());
			asteroidTable[18][1] = String.valueOf(data.getMissDistance().getMiles());
			asteroidTable[19][1] = data.getOrbiting_body();
			
			
			

	}

}
