package fitbot.ldbs.rest.output;

public class BasicResponse {

	private String status;
	
	private String message = null;
	
	public BasicResponse(){
		this.status = "OK";
	}
	
	public BasicResponse(String message){
		this.status = "ERROR";
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
