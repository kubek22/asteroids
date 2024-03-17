package pl.edu.pw.mini.zpoif.project.part1.inner_classes.close_approach_data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CloseApproachData {
	
	private LocalDate close_approach_date;
	private LocalDateTime close_approach_date_full;
	private long epoch_date_close_approach;
	private RelativeVelocity relativeVelocity;
	private MissDistance missDistance;
	private String orbiting_body;
	
	
	public CloseApproachData(LocalDate close_approach_date, LocalDateTime close_approach_date_full,
			long epoch_date_close_approach, RelativeVelocity relativeVelocity, MissDistance missDistance,
			String orbiting_body) {
		super();
		this.close_approach_date = close_approach_date;
		this.close_approach_date_full = close_approach_date_full;
		this.epoch_date_close_approach = epoch_date_close_approach;
		this.relativeVelocity = relativeVelocity;
		this.missDistance = missDistance;
		this.orbiting_body = orbiting_body;
	}


	public LocalDate getClose_approach_date() {
		return close_approach_date;
	}


	public LocalDateTime getClose_approach_date_full() {
		return close_approach_date_full;
	}


	public long getEpoch_date_close_approach() {
		return epoch_date_close_approach;
	}


	public RelativeVelocity getRelativeVelocity() {
		return relativeVelocity;
	}


	public MissDistance getMissDistance() {
		return missDistance;
	}


	public String getOrbiting_body() {
		return orbiting_body;
	}
	
	
}
