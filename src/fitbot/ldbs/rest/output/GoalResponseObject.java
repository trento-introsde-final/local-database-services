package fitbot.ldbs.rest.output;

import java.sql.Timestamp;

import fitbot.ldbs.model.Goal;

public class GoalResponseObject {

	private int id;
	
	private Timestamp created;
	
	private float target;
	
	private String measure_type;
	
	private String name;
	
	private String units;
	
	private String period;
	
	private int period_days;
	
	public GoalResponseObject(){
		
	}

	public int getId() {
		return id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public float getTarget() {
		return target;
	}

	public String getMeasure_type() {
		return measure_type;
	}

	public String getName() {
		return name;
	}

	public String getUnits() {
		return units;
	}

	public String getPeriod() {
		return period;
	}

	public int getPeriod_days() {
		return period_days;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public void setTarget(float target) {
		this.target = target;
	}

	public void setMeasure_type(String measure_type) {
		this.measure_type = measure_type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setPeriod_days(int period_days) {
		this.period_days = period_days;
	}
	
	public static GoalResponseObject convert(Goal g){
		GoalResponseObject gRes = new GoalResponseObject();
		gRes.setId(g.getId());
		gRes.setCreated(g.getCreationDate());
		gRes.setTarget(g.getTargetValue());
		gRes.setMeasure_type(g.getGoalType().getId());
		gRes.setName(g.getGoalType().getName());
		gRes.setUnits(g.getGoalType().getUnits());
		gRes.setPeriod(g.getGoalPeriod().getName());
		gRes.setPeriod_days(g.getGoalPeriod().getDaysLength());
		return gRes;
	}
}
