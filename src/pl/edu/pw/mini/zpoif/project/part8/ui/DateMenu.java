package pl.edu.pw.mini.zpoif.project.part8.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import pl.edu.pw.mini.zpoif.project.part1.Asteroid;
import pl.edu.pw.mini.zpoif.project.part1.connection.Connection;
import pl.edu.pw.mini.zpoif.project.part1.connection.IncorrectDateException;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;
import pl.edu.pw.mini.zpoif.project.part3.parser.Parser;

public class DateMenu extends JPanel{
	
	LocalDate dateFrom = LocalDate.now();
	LocalDate dateTo = LocalDate.now();
	
	LocalDate preAcceptDateFrom = dateFrom;
	LocalDate preAcceptDateTo = dateTo;	
	
	
	
	JPanel getDateFilterMenu(MainFrame mainFrame) {
		
		JPanel jPanel = new JPanel();
		
		jPanel.setLayout(new BorderLayout());
		
		jPanel.add(getTextPanel(Color.BLACK, 20, "Choose dates"), BorderLayout.NORTH);
		
		JPanel subPanel = new JPanel();
		
		FlowLayout subPanelGridLayout = new FlowLayout();
		subPanelGridLayout.setHgap(5);
		subPanelGridLayout.setVgap(5);

		
		UtilDateModel modelFrom = new UtilDateModel();
		UtilDateModel modelTo = new UtilDateModel();
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		//domyslne jako dzis
		
		
		JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
		JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
		JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateComponentFormatter());
		JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateComponentFormatter());
		
		modelFrom.setValue(Date.valueOf(dateFrom));
		modelTo.setValue(Date.valueOf(dateTo));
		
		
		subPanel.setLayout(subPanelGridLayout);
		
		subPanel.add(getTextPanel(Color.BLACK, 15, "from:"));
		
		subPanel.add(datePickerFrom);
		
		ActionListener dateChangeAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//miesi¹ce od 0 do 11
				
				if (e.getSource()==datePanelFrom) {
					int month = datePickerFrom.getModel().getMonth() + 1;
					int year = datePickerFrom.getModel().getYear();
					int day = datePickerFrom.getModel().getDay();

					preAcceptDateFrom = LocalDate.of(year, month, day);
				}
				if (e.getSource()==datePanelTo) {
					int month = datePickerTo.getModel().getMonth() + 1;
					int year = datePickerTo.getModel().getYear();
					int day = datePickerTo.getModel().getDay();

					preAcceptDateTo = LocalDate.of(year, month, day);
				}
				}
				
				
			};
		
		datePickerFrom.addActionListener(dateChangeAction);
		datePickerTo.addActionListener(dateChangeAction);
		
		subPanel.add(getTextPanel(Color.BLACK, 15, "to:"));
		
		subPanel.add(datePickerTo);

		JButton acceptDateButton = new JButton("accept date");
		
		ActionListener dateAcceptAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((preAcceptDateFrom.plusDays(8).isAfter(preAcceptDateTo) && preAcceptDateFrom.isBefore(preAcceptDateTo) 
						|| preAcceptDateTo.isEqual(preAcceptDateFrom))) {
					
					dateFrom = preAcceptDateFrom;
					dateTo = preAcceptDateTo;
					
					
					
					try {
						mainFrame.bottomPanel.remove(0);
						mainFrame.bottomPanel.add(mainFrame.listPanel.getAsteroidListPanel(dateFrom, dateTo, mainFrame, null), 0);
						
						mainFrame.listPanel.originalAsteroids = mainFrame.listPanel.asteroids;
					} catch (IOException | IncorrectDateException | NoAsteroidsException e1) {
						popupWindow("error","wrong dates");
						e1.printStackTrace();
					}
					
					mainFrame.revalidate();
					mainFrame.repaint();
				}
				else if (dateTo.isBefore(dateFrom)){
					popupWindow("wrong date input","date 'from' must be before or be equal date 'to'");

				}
				else {
					popupWindow("wrong date input","dates must be within 7 days difference");
				}
				
			}};
		
		acceptDateButton.addActionListener(dateAcceptAction);	
			
		subPanel.add(acceptDateButton);
		
		jPanel.add(subPanel, BorderLayout.CENTER);
		
		return jPanel;
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

}
