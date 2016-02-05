package fitbot.ldbs.rest.output;

import java.util.ArrayList;
import java.util.List;

import fitbot.ldbs.model.Run;

public class RunsResponse extends BasicResponse{

	private List<RunResponseObject> runs;
	
	public RunsResponse(){
		super();
	}

	public List<RunResponseObject> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runsModel) {
		this.runs = new ArrayList<RunResponseObject>();
		for(Run r: runsModel){
			this.runs.add(RunResponseObject.convert(r));
		}
	}
	
	
}
