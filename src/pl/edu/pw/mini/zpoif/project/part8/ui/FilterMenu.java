package pl.edu.pw.mini.zpoif.project.part8.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.AsteroidField;
import pl.edu.pw.mini.zpoif.project.part1.connection.IncorrectDateException;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;
import pl.edu.pw.mini.zpoif.project.part5.filters.Filter;
import pl.edu.pw.mini.zpoif.project.part5.filters.IncorrectFilterArgumentException;
import pl.edu.pw.mini.zpoif.project.part7.sorter.Sorter;

public class FilterMenu extends JPanel{
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private String[] possibleFilters = {"Diameter Max in m", "Absolute Magnitude", "Miss Distance in km", "Relative Velocity km/s"};
	private String[] possibleSorters = {"Diameter Max in m", "Absolute Magnitude", "Miss Distance in km", "Relative Velocity km/s","Approach Date"};

	
	String minSelectedValue = new Double(0).toString();
	String maxSelectedValue = new Double(0).toString();
	
	
	LocalDate dateFrom;
	LocalDate dateTo;

	
	String selectedFiltrItem = possibleFilters[0];
	String selectedSortItem = possibleSorters[0];
	
	
	AsteroidField selectedFiltrItemFieldName;
	AsteroidField selectedSortItemFieldName;

	Filter filter = new Filter();
	
	Boolean ascending = true;
	
	String hazardousChoice;
	
	List<Asteroid> preHazardousFiltrAsteroid;



	
    JComboBox jFilterBox = new JComboBox(possibleFilters);
    JComboBox jSorterBox = new JComboBox(possibleSorters);
    
    //accept button
    JButton acceptButton = new JButton("accept filter");
    //reset button
    JButton resetButton = new JButton("reset filters");
    
    //asc button
    JButton ascButton = new JButton("ascending order");
    JButton descButton = new JButton("descending order");

    JButton downLoadButton = new JButton("download");

    
    //hazardous buttons
    JButton yesButton = new JButton("yes");
    JButton noButton = new JButton("no");
    JButton bothButton = new JButton("both");
    
    // min max text input
    JFormattedTextField minField = new JFormattedTextField("min");
    JFormattedTextField maxField = new JFormattedTextField("max");
    
	JPanel getFilterPanel(MainFrame mainFrame) {
			
		
			
			JPanel subPanel = new JPanel();
			
			
			GridLayout subPanelGridLayout = new GridLayout(3,5);
			subPanelGridLayout.setHgap(8);
			subPanelGridLayout.setVgap(8);
			
			subPanel.setLayout(subPanelGridLayout);
			
			jFilterBox.setSelectedIndex(0);
		    jSorterBox.setSelectedIndex(0);
		    
		    
		    //pierwszy wiersz
		    subPanel.add(getTextPanel(Color.BLACK, 18, "Filter by"));
		    subPanel.add(jFilterBox);
		    subPanel.add(minField);
		    subPanel.add(maxField);
		    subPanel.add(acceptButton);
		    
		    
		    //drugi wiersz
		    subPanel.add(getTextPanel(Color.BLACK, 18, "Possibly hazardous?"));
		    subPanel.add(yesButton);
		    subPanel.add(noButton);
		    subPanel.add(bothButton);
		    subPanel.add(resetButton);

		    //trzeci wiersz
		    subPanel.add(getTextPanel(Color.BLACK, 18, "Sort by"));
		    subPanel.add(jSorterBox);
		    subPanel.add(ascButton);
		    subPanel.add(descButton);
		    subPanel.add(new JLabel(""));

	
		    //listenery
		    
		    //wybor z list
		    
		    ActionListener jComboBoxListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource()==jFilterBox) {
						selectedFiltrItem = jFilterBox.getModel().getSelectedItem().toString();
						
					}
					else if (e.getSource()==jSorterBox) {
						selectedSortItem = jSorterBox.getModel().getSelectedItem().toString();
						

					}
				}
		    	
		    };
		    
		    
		    
		    
		    
		    //min max wybor
		    PropertyChangeListener valueChange = new PropertyChangeListener() {
		    	
				@Override
				public void propertyChange(PropertyChangeEvent e) {
					Object source = e.getSource();
					if (source == minField) {
						minSelectedValue = minField.getValue().toString();	
					}
					else if (source == maxField) {
						maxSelectedValue = maxField.getValue().toString();
					}
					
					
				};
		    	
		    };
		    
		    FocusListener minMaxFocusListener = new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (e.getSource() == minField) {
						minField.setValue("");
					}
					else if (e.getSource() == maxField) {
						maxField.setValue("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
		    	
		    };
		    
		    
		    
		    //accept min max
		    ActionListener acceptListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
	

					if (minSelectedValue == null || maxSelectedValue == null) {
						popupWindow("error","wrong data");

					}
					
					else if (!pattern.matcher(minSelectedValue).matches() 
							|| !pattern.matcher(minSelectedValue).matches()) {
						popupWindow("error","only numbers allowed");
					}
					
					else if (Double.valueOf(minSelectedValue) < 0 || Double.valueOf(maxSelectedValue) < 0) {
						popupWindow("error","only positive numbers allowed");

					}
					
					else if (Double.valueOf(minSelectedValue) > Double.valueOf(maxSelectedValue)) {
						
						popupWindow("error","'min' must be <= 'max'");
						
					}
					
					
					else if (Double.valueOf(minSelectedValue) <= Double.valueOf(maxSelectedValue)) {
						if (selectedFiltrItem == possibleFilters[0]) {
							selectedFiltrItemFieldName =  AsteroidField.estimated_diameter_max_m;	
						}	
						else if (selectedFiltrItem == possibleFilters[1]) {
							selectedFiltrItemFieldName = AsteroidField.absolute_magnitude_h;
						}
						
						else if (selectedFiltrItem == possibleFilters[2]) {
							selectedFiltrItemFieldName = AsteroidField.kilometers_miss_distance;
						}
						
						else if (selectedFiltrItem == possibleFilters[3]) {
							selectedFiltrItemFieldName = AsteroidField.kilometers_per_second;
						}
						
						List<Asteroid> asteroid = mainFrame.listPanel.asteroids;
						
						
						try {
							

							asteroid = Filter.rangeFilterBy(asteroid, selectedFiltrItemFieldName, minSelectedValue, maxSelectedValue);
							
							
							preHazardousFiltrAsteroid = asteroid;							

							
							


							changeAsteroidList(asteroid,mainFrame);
							

							
						} catch (IncorrectFilterArgumentException e1) {
							popupWindow("error","wrong data");
							e1.printStackTrace();
						}
						
					}
					else {
						popupWindow("error","wrong input data");
					}
					
				};
		    	
		    };
		    
		    
		    
		    //hazardous
		    
		    
		    
		    ActionListener isHazardousListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					AsteroidField fieldHazardous = AsteroidField.is_potentially_hazardous_asteroid;
					Object source = e.getSource();
					if (source == yesButton) {
						hazardousChoice = "true";
					}
					else if (source == noButton) {
						hazardousChoice = "false";

					}
					
					
						
					
					
						
					List<Asteroid> asteroid = mainFrame.listPanel.asteroids;
					if (preHazardousFiltrAsteroid == null) {
						preHazardousFiltrAsteroid = asteroid;
					}
					asteroid = preHazardousFiltrAsteroid;
					
					try {
						asteroid = Filter.filterBy(asteroid, fieldHazardous, hazardousChoice);
						changeAsteroidList(asteroid,mainFrame);
					} catch (IncorrectFilterArgumentException e1) {
						popupWindow("error","wrong filter data");
						e1.printStackTrace();
					}
						
						
					
					
				};
		    	
		    };
		    
		    
		    ActionListener isHazardousBothListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (preHazardousFiltrAsteroid != null) {
						List<Asteroid> asteroid = preHazardousFiltrAsteroid;
						
						preHazardousFiltrAsteroid = null;
						

						changeAsteroidList(asteroid,mainFrame);

					}
					
				}
		    	
		    };
		    
		    
		    
		    // asc desc buttons
		    
		    ActionListener ascDescButtonListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (selectedSortItem == possibleSorters[0]) {
						selectedSortItemFieldName =  AsteroidField.estimated_diameter_max_m;	
					}
					
					if (selectedSortItem == possibleSorters[1]) {
						selectedSortItemFieldName = AsteroidField.absolute_magnitude_h;
					}
					
					if (selectedSortItem == possibleSorters[2]) {
						selectedSortItemFieldName = AsteroidField.kilometers_miss_distance;
					}
					
					if (selectedSortItem == possibleSorters[3]) {
						selectedSortItemFieldName = AsteroidField.kilometers_per_second;
					}
					if (selectedSortItem == possibleSorters[4]) {
						selectedSortItemFieldName = AsteroidField.close_approach_date;
					}
					
					
					
					if (e.getSource()==ascButton) {
						ascending = true;
					}
					else {
						ascending = false;
					}
					
					

					
					

					sortAsteroidList(mainFrame, selectedSortItemFieldName, ascending);
					
					
					mainFrame.revalidate();
					mainFrame.repaint();
					
				}
		    	
		    };
		    
		    
		    
		    
		    
		    
		
		    
		  
		    
		    //resetFilter
		    ActionListener resetListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (mainFrame.listPanel.originalAsteroids.size() == mainFrame.listPanel.asteroids.size()) {
						
						popupWindow("error","no filters have been applied");

					}
					else {
						//
						minField.setValue("min");
						maxField.setValue("max");
						
						
						
						// resetowanie listy
						List<Asteroid> asteroid = mainFrame.listPanel.originalAsteroids;
						
						changeAsteroidList(asteroid,mainFrame);
						
					}
				}
					
					
			};
		    	
		    
			/*
			downLoadButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

					
					CharSequence xmlToFile = mainFrame.listPanel.xml;
					
					
					int returnValue = jfc.showSaveDialog(FilterMenu.this);
					
					
					String fileName = jfc.getSelectedFile().getAbsolutePath();
					
					Path path = Paths.get(fileName);

					
					jfc.addChoosableFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
					
					jfc.setAcceptAllFileFilterUsed(true);
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						
	
						

				        try {
							Files.writeString(path, xmlToFile,StandardCharsets.UTF_8);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        
						
					}
					
				}
				
			});
			
			*/
		    
		    
		    //minField.addPropertyChangeListener(testChange);
			jFilterBox.addActionListener(jComboBoxListener);
		    minField.addPropertyChangeListener(valueChange);
		    maxField.addPropertyChangeListener(valueChange);
		    minField.addFocusListener(minMaxFocusListener);
		    maxField.addFocusListener(minMaxFocusListener);
		    acceptButton.addActionListener(acceptListener);
		    
		    yesButton.addActionListener(isHazardousListener);
		    noButton.addActionListener(isHazardousListener);
		    bothButton.addActionListener(isHazardousBothListener);
		    
		   
		    jSorterBox.addActionListener(jComboBoxListener);
		    ascButton.addActionListener(ascDescButtonListener);
		    descButton.addActionListener(ascDescButtonListener);
		    resetButton.addActionListener(resetListener);

		    
		    
		    
			
			
			return subPanel;
		}

	private JLabel getTextPanel(Color color, int size, String text) {
		
		JLabel jlabel = new JLabel("",SwingConstants.CENTER);
		
		jlabel.setText(text);
		
		jlabel.setForeground(color);
		
		
		jlabel.setFont(new Font("Arial",Font.PLAIN, size));         
		
		return jlabel;
		
	}
	
	private void popupWindow(String title, String text) {
		JFrame jFrame = new JFrame(title);
		jFrame.add(new JLabel(text, SwingConstants.CENTER));
		jFrame.setSize(250, 100);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	private void updateDate(MainFrame mainFrame) {
		
		dateTo = mainFrame.dateMenu.dateTo;
		dateFrom = mainFrame.dateMenu.dateFrom;
		
	}
	
	
	private void changeAsteroidList(List<Asteroid> asteroids, MainFrame mainFrame) {
		updateDate(mainFrame);
		
		
		mainFrame.listPanel.updateList(asteroids); 
		mainFrame.bottomPanel.remove(0);
		mainFrame.bottomPanel.add(mainFrame.listPanel.sp,0);
		
		//mainFrame.asteroidListPanel = mainFrame.listPanel.sp;
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}
	
	private void sortAsteroidList(MainFrame mainFrame, AsteroidField field, boolean ascending) {
		updateDate(mainFrame);
		
		
		mainFrame.listPanel.sortList(field, ascending); 
		mainFrame.bottomPanel.remove(0);
		mainFrame.bottomPanel.add(mainFrame.listPanel.sp,0);
		
		//mainFrame.asteroidListPanel = mainFrame.listPanel.sp;
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}

}
