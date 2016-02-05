package fitbot.ldbs.rest.output;

public class SlackIdResponse extends BasicResponse{

	private String id;
	
	public SlackIdResponse(){
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
