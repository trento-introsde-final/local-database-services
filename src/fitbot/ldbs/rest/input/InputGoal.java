package fitbot.ldbs.rest.input;

import fitbot.ldbs.model.Goal;
import fitbot.ldbs.model.GoalPeriod;
import fitbot.ldbs.rest.exceptions.InputFormatException;

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
	
	public Goal toGoal() throws InputFormatException{
		Goal g = new Goal();
		g.setTargetValue(target);
		GoalPeriod gp = GoalPeriod.getGoalPeriodByName(period);
		if(gp == null){
			throw new InputFormatException("Unrecognized goal period.");
		}
		g.setGoalPeriod(gp);
		return g;
	}
}
