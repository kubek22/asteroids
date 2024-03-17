package pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data;

public class MissDistance {
	
	private double astronomical;
	private double lunar;
	private double kilometers;
	private double miles;
	
	public MissDistance(double astronomical, double lunar, double kilometers, double miles) {
		super();
		this.astronomical = astronomical;
		this.lunar = lunar;
		this.kilometers = kilometers;
		this.miles = miles;
	}

	public double getAstronomical() {
		return astronomical;
	}

	public double getLunar() {
		return lunar;
	}

	public double getKilometers() {
		return kilometers;
	}

	public double getMiles() {
		return miles;
	}

	
}
