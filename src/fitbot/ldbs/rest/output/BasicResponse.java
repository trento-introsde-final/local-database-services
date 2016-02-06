package fitbot.ldbs.rest.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BasicResponse {

	private String status;
	
	@JsonInclude(Include.NON_NULL)
	private String error = null;
	
	public BasicResponse(){
		this.status = "OK";
	}
	
	public BasicResponse(String message){
		this.status = "ERROR";
		this.error = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return error;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.status = "ERROR";
		this.error = message;
	}

}
