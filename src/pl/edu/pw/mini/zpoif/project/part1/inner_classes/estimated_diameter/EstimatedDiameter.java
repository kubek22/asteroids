package pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter;

public abstract class EstimatedDiameter {

	private double estimated_diameter_min;
	private double estimated_diameter_max;
	private Unit unit;
	
	public enum Unit{
		FEET, KILOMETER, METER, MILE;
	}
	
	public EstimatedDiameter(double estimated_diameter_min, double estimated_diameter_max) {
		super();
		this.estimated_diameter_min = estimated_diameter_min;
		this.estimated_diameter_max = estimated_diameter_max;
	}


	public double getEstimated_diameter_min() {
		return estimated_diameter_min;
	}


	public double getEstimated_diameter_max() {
		return estimated_diameter_max;
	}


	public abstract Unit getUnit();
	
}
