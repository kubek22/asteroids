package pl.edu.pw.mini.zpoif.project.part8.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.edu.pw.mini.zpoif.project.part1.connection.IncorrectDateException;
import pl.edu.pw.mini.zpoif.project.part3.parser.NoAsteroidsException;

public class MainFrame extends JFrame{
	
	
	DateMenu dateMenu = new DateMenu();
	FilterMenu filterMenu = new FilterMenu();

	AsteroidInformation informationPanel = new AsteroidInformation();
	AsteroidList listPanel = new AsteroidList();
	
	JPanel menuPanel;
	JPanel bottomPanel;
	
	JScrollPane asteroidListPanel;
	
	
	public void setAsteroidListPanel(JScrollPane asteroidListPanel) {
		this.asteroidListPanel = asteroidListPanel;
	}

	public void go() throws IOException, IncorrectDateException, NoAsteroidsException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("NASA API ASTEROIDS");
		this.setVisible(true);
		this.setPreferredSize(new Dimension(900,900));
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.getContentPane().setLayout(boxLayout);
		
		this.getContentPane().add(getMainTitlePanel());
	
		menuPanel = getMenuPanel();
		
		this.getContentPane().add(menuPanel);
		
		bottomPanel = getBottomPanel(); 
		
		this.getContentPane().add(bottomPanel);
		
		
		
		this.setMinimumSize(new Dimension(1200,800));
		
	}
	
	JPanel getMenuPanel() {
			
			JPanel jPanel = new JPanel();
			
			BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
			
			jPanel.setLayout(boxLayout);
			
			jPanel.add(dateMenu.getDateFilterMenu(this));
			
			jPanel.add(filterMenu.getFilterPanel(this));
			
	
			
			return jPanel;
		}
		
	
	JPanel getBottomPanel() throws IOException, IncorrectDateException, NoAsteroidsException {
		
		JPanel jPanel = new JPanel();
		
		
		this.asteroidListPanel = listPanel.getAsteroidListPanel(dateMenu.dateFrom, dateMenu.dateTo, this, null);
		
		jPanel.add(this.asteroidListPanel,0);
		
		
		jPanel.add(informationPanel.getAsteroidMainPanel(this),1);
		
		return jPanel;
		
	}
	
	private JLabel getTextPanel(Color color, int size, String text) {
		
		JLabel jlabel = new JLabel("", SwingConstants.CENTER);
		
		jlabel.setText(text);
		
		jlabel.setForeground(color);
		
		jlabel.setFont(new Font("Arial",Font.PLAIN, size));         
		
		return jlabel;
		
	}
	
	private JPanel getMainTitlePanel() {
		
		JPanel mainPanel = new JPanel();
	
		mainPanel.setVisible(true);
		
		mainPanel.setLayout(new BorderLayout() );
	
		mainPanel.add(getTextPanel(Color.BLACK, 30, "NASA ASTEROID RADAR API APPLICATION"), BorderLayout.NORTH);
		
		
		
		return mainPanel;
		
	}
	
	
	
	
}
