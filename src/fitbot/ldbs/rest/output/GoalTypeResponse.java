package fitbot.ldbs.rest.output;

public class GoalTypeResponse extends BasicResponse {

	private String id;
	
	private String name;
	
	private String units;
	
	public GoalTypeResponse(){
		super();
	}
	
	public GoalTypeResponse(String message){
		super(message);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUnits() {
		return units;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	
}
