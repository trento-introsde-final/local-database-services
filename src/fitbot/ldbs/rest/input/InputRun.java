package fitbot.ldbs.rest.input;

import java.sql.Timestamp;

import fitbot.ldbs.model.Run;

public class InputRun {

	private Timestamp start_date;
	
	private float distance;
	
	private float calories;
	
	private int moving_time;
	
	private float elevation_gain;

	private float max_speed;
	
	private float avg_speed;

	public InputRun(){
		
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public float getDistance() {
		return distance;
	}

	public float getCalories() {
		return calories;
	}

	public int getMoving_time() {
		return moving_time;
	}

	public float getElevation_gain() {
		return elevation_gain;
	}

	public float getMax_speed() {
		return max_speed;
	}

	public float getAvg_speed() {
		return avg_speed;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public void setMoving_time(int moving_time) {
		this.moving_time = moving_time;
	}

	public void setElevation_gain(float elevation_gain) {
		this.elevation_gain = elevation_gain;
	}

	public void setMax_speed(float max_speed) {
		this.max_speed = max_speed;
	}

	public void setAvg_speed(float avg_speed) {
		this.avg_speed = avg_speed;
	}
	
	public Run toRun(){
		Run r = new Run();
		r.setStartdate(start_date);
		r.setDistance(distance);
		r.setCalories(calories);
		r.setMovingTime(moving_time);
		r.setElevationGain(elevation_gain);
		r.setMaxSpeed(max_speed);
		r.setAvgSpeed(avg_speed);
		return r;
	}
	
}
