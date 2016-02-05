package fitbot.ldbs.rest.output;

import java.util.List;

import fitbot.ldbs.model.GoalType;

public class GoalTypesResponse extends BasicResponse{
	
	private List<GoalType> results;
	
	public GoalTypesResponse(){
		super();
	}
	
	public GoalTypesResponse(String message){
		super(message);
	}

	public List<GoalType> getResults() {
		return results;
	}

	public void setResults(List<GoalType> results) {
		this.results = results;
	}

}
