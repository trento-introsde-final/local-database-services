package fitbot.ldbs.rest.input;

import fitbot.ldbs.model.Goal;
import fitbot.ldbs.model.GoalPeriod;

public class InputGoal {

	private Float target;
	
	private String period;
	
	public InputGoal(){
		
	}

	public Float getTarget() {
		return target;
	}

	public String getPeriod() {
		return period;
	}

	public void setTarget(Float target) {
		this.target = target;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	public Goal toGoal(){
		Goal g = new Goal();
		g.setTargetValue(target);
		GoalPeriod gp = GoalPeriod.getGoalPeriodByName(period);
		g.setGoalPeriod(gp);
		return g;
	}
}
