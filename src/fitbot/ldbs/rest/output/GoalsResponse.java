package fitbot.ldbs.rest.output;

import java.util.ArrayList;
import java.util.List;

import fitbot.ldbs.model.Goal;

public class GoalsResponse extends BasicResponse{

	private List<GoalResponseObject> goals;
	
	public GoalsResponse(){
		super();
	}

	public List<GoalResponseObject> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goalModels) {
		goals = new ArrayList<GoalResponseObject>();
		for(Goal g: goalModels){
			goals.add(GoalResponseObject.convert(g));
		}
	}
	
	
}
