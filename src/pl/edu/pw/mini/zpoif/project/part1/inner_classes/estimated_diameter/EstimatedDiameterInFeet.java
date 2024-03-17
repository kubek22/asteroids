package pl.edu.pw.mini.zpoif.project.part1.inner_classes.estimated_diameter;

public class EstimatedDiameterInFeet extends EstimatedDiameter{

	public EstimatedDiameterInFeet(double estimated_diameter_min, double estimated_diameter_max) {
		super(estimated_diameter_min, estimated_diameter_max);
	}

	@Override
	public Unit getUnit() {
		return Unit.FEET;
	}

}
