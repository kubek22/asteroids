package pl.edu.pw.mini.zpoif.project.part8.ui;



import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;
import pl.edu.pw.mini.zpoif.project.part1.connection.Connection;
import pl.edu.pw.mini.zpoif.project.part1.connection.IncorrectDateException;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;
import pl.edu.pw.mini.zpoif.project.part3.parser.Parser;
import pl.edu.pw.mini.zpoif.project.part5.filters.Filter;
import pl.edu.pw.mini.zpoif.project.part5.filters.IncorrectFilterArgumentException;
import pl.edu.pw.mini.zpoif.project.part7.sorter.Sorter;

public class AsteroidList extends JTable{


	String xml;
	
	List<Asteroid> asteroids;
	
	List<Asteroid> originalAsteroids;
	
	Object[][] table;
	
	JScrollPane sp;
	
	
	JTable jTable;
	
	String[] columns = {"id","name", "absolute magnitude h", "is potentially hazardous","close approach date", 
			 "max diameter","relative velocity km/s",
			"relative velocity km/h","miss distance in km"};
	
	MouseListener mouseListener;
	
	
	JScrollPane getAsteroidListPanel(LocalDate dateFrom, LocalDate dateTo, MainFrame mainFrame, List<Asteroid> asterFiltr) throws IOException, IncorrectDateException, NoAsteroidsException {
			
		
			
			
			if (asterFiltr == null) {
				xml = Connection.downloadXMLData(dateFrom.toString(), dateTo.toString());
				asteroids = Parser.parse(xml);
				
			}
			
			if (originalAsteroids == null) {
				originalAsteroids = asteroids;
			}
			
			if (asterFiltr != null) { 
				
				asteroids = asterFiltr;
					
				
			}
			
			updateList(null);
			
			jTable = new JTable(table,columns);

			setupJTableColumnWidth();
			
			sp = new JScrollPane(jTable);
			
			this.mouseListener = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
					
					
					int row = jTable.rowAtPoint(e.getPoint());
					//int row = result.getValueAt(r.getSelectedRow(), 0);
					if (row > -1 && row < table.length) {
						
						AsteroidField idField = AsteroidField.id;
						
						int id = (int) table[row][0];
						
						try {
							Asteroid ast = Filter.filterBy(asteroids, idField, String.valueOf(id)).get(0);
							mainFrame.informationPanel.updateTables(ast);
							mainFrame.revalidate();
							mainFrame.repaint();
						} catch (IncorrectFilterArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				
				
			};
			
			jTable.addMouseListener(mouseListener);
			
			
			return sp;
			
		}

	
	
	public void updateList(List<Asteroid> astter) {
		
		
		if (astter != null) {
			this.asteroids = astter;


		}
		
		Object[][] tempTable = new Object[this.asteroids.size()][this.columns.length];
		
		Iterator<Asteroid> it = this.asteroids.iterator();
		
		
		int i = 0;
		while (it.hasNext()) {
			
			Asteroid ast = (Asteroid) it.next();


			tempTable[i][0] = ast.getId();
			tempTable[i][1] = ast.getName();
			tempTable[i][2] = ast.getAbsolute_magnitude_h();
			tempTable[i][3] = ast.is_potentially_hazardous_asteroid();
			tempTable[i][4] = ast.getCloseApproachData().getClose_approach_date();
			tempTable[i][5] = ast.getEstimatedDiameterInMeters().getEstimated_diameter_max();
			tempTable[i][6] = ast.getCloseApproachData().getRelativeVelocity().getKilometers_per_second();
			tempTable[i][7] = ast.getCloseApproachData().getRelativeVelocity().getKilometers_per_hour();
			tempTable[i][8] = ast.getCloseApproachData().getMissDistance().getKilometers();
			i++;
			
		}
		
		setTable(tempTable);
		setjTable(new JTable(this.table, this.columns));
		setSp(new JScrollPane(this.jTable));
		setupJTableColumnWidth();
		jTable.addMouseListener(mouseListener);

		
		
	}
	
	public void sortList(AsteroidField field, boolean ascending) {
		Sorter.sortBy(this.asteroids, field, ascending);
		updateList(null);
	}
	
	public List<Asteroid> getAsteroids() {
		return asteroids;
	}



	public void setSp(JScrollPane sp) {
		this.sp = sp;
	}



	public void setTable(Object[][] table) {
		this.table = table;
	}



	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}



	public void setupJTableColumnWidth() {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(140);
		jTable.getColumnModel().getColumn(6).setPreferredWidth(140);
		jTable.getColumnModel().getColumn(7).setPreferredWidth(140);
		jTable.getColumnModel().getColumn(8).setPreferredWidth(140);
		jTable.setSize(new Dimension(800,400));
		jTable.setEnabled(false);
	}
	
	
	
	
}
