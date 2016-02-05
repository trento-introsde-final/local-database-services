package fitbot.ldbs.rest.output;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import fitbot.ldbs.model.Goal;

public class GoalsResponse extends BasicResponse{

	@JsonInclude(Include.NON_NULL)
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
