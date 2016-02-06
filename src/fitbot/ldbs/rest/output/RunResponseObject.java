package fitbot.ldbs.rest.output;

import java.sql.Timestamp;

import fitbot.ldbs.model.Run;

public class RunResponseObject {

	private int id;
	
	private Timestamp start_date;
	
	private Float distance;
	
	private Float calories;
	
	private int moving_time;
	
	private Float elevation_gain;
	
	private Float max_speed;
	
	private Float avg_speed;
	
	private Integer steps;
	
	public RunResponseObject(){
		
	}

	public int getId() {
		return id;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public Float getDistance() {
		return distance;
	}

	public Float getCalories() {
		return calories;
	}

	public int getMoving_time() {
		return moving_time;
	}

	public Float getElevation_gain() {
		return elevation_gain;
	}

	public Float getMax_speed() {
		return max_speed;
	}

	public Float getAvg_speed() {
		return avg_speed;
	}
	
	public Integer getSteps(){
		return steps;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public void setCalories(Float calories) {
		this.calories = calories;
	}

	public void setMoving_time(int moving_time) {
		this.moving_time = moving_time;
	}

	public void setElevation_gain(Float elevation_gain) {
		this.elevation_gain = elevation_gain;
	}

	public void setMax_speed(Float max_speed) {
		this.max_speed = max_speed;
	}

	public void setAvg_speed(Float avg_speed) {
		this.avg_speed = avg_speed;
	}
	
	public void setSteps(Integer steps){
		this.steps = steps;
	}
	
	public static RunResponseObject convert(Run r){
		RunResponseObject runRes = new RunResponseObject();
		runRes.setId(r.getId());
		runRes.setStart_date(r.getStartdate());
		runRes.setDistance(r.getDistance());
		runRes.setCalories(r.getCalories());
		runRes.setMoving_time(r.getMovingTime());
		runRes.setAvg_speed(r.getAvgSpeed());
		runRes.setMax_speed(r.getMaxSpeed());
		runRes.setElevation_gain(r.getElevationGain());
		runRes.setSteps(r.getSteps());
		return runRes;
	}
	
}
