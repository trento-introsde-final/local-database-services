package fitbot.ldbs.rest;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.Goal;
import fitbot.ldbs.model.GoalType;
import fitbot.ldbs.model.Person;
import fitbot.ldbs.model.Run;
import fitbot.ldbs.rest.input.InputGoal;
import fitbot.ldbs.rest.input.InputRun;
import fitbot.ldbs.rest.input.InputUser;
import fitbot.ldbs.rest.output.BasicResponse;
import fitbot.ldbs.rest.output.GoalsResponse;
import fitbot.ldbs.rest.output.RunsResponse;


@Stateless
@LocalBean
public class UsersResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public UsersResource(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }
    
    /**
     * Create a new Person. 
     * On success returns status 201 and Location header set to the URI
     * of the newly created Person.
     * 
     * @param person
     * @return Returns the created Person
     * @throws IOException
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response newPerson(InputUser user) {
    	Response res;
    	BasicResponse bResp = new BasicResponse();
    	URI location = null;
    	Person p = user.toPerson();
    	if(p.getSlack_user_id() == null){
    		bResp.setMessage("Must provide at least slack_user_id");
    		return Response.status(400).entity(bResp).build();
    	}
    	try {
    		p = Person.savePerson(p);
    		location = new URI(uriInfo.getAbsolutePath().toString()+p.getId());
    	} catch (Exception e){
        	bResp = new BasicResponse(e.getMessage());
    		res = Response.serverError().entity(bResp).build();
    		return res;
    	}
    	res = Response.created(location).entity(p).build();
		return res;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{userId}")
    public Response updatePerson(@PathParam("userId") int userId, InputUser user){
    	Response res;
    	BasicResponse bResp = new BasicResponse();
    	Person p = Person.getPersonById(userId);
    	if(p == null){
    		bResp.setMessage("Unknown user id.");
    		return Response.status(404).entity(bResp).build();
    	}
    	Person newPerson = user.toPerson();
    	newPerson.setId(p.getId());
    	try{
    		Person.updatePerson(newPerson);
    	} catch (Exception e){
    		bResp.setMessage("Error saving user. "+e.getMessage());
    		return Response.status(500).entity(bResp).build();
    	}
    	res = Response.ok(bResp).build();
    	return res;
    }
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{userId}/goals/{goalType}")
    public Response setPersonalGoal(
    		@PathParam("userId") int userId, 
    		@PathParam("goalType") String goalType, InputGoal iGoal){
    	BasicResponse bResp = new BasicResponse();
    	
    	//Fetch user and goal objects
    	Person p = Person.getPersonById(userId);
    	GoalType gType = GoalType.getGoalTypeById(goalType);
    	
    	//Do they exist?
    	if(p == null || gType == null){
    		String error = "";
    		if(p == null){
    			error += "Incorrect user id. ";
    		}
    		if(gType == null){
    			error += "Incorrect goal type.";
    		}
    		bResp.setMessage(error);
    		return Response.status(404).entity(bResp).build();
    	}
    	
    	try {
        	//Save goal
    		Goal g = iGoal.toGoal();
        	g.setGoalType(gType);
    		p.setUserGoal(g);
    	} catch (Exception e){
    		bResp.setMessage(e.getMessage());
    		return Response.status(500).entity(bResp).build();
    	}
    	return Response.ok(bResp).build();
    }

    @GET
    @Path("{userId}/goals")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonalGoals(@PathParam("userId") int userId) {
    	Response res;
    	GoalsResponse gResp = new GoalsResponse();
    	Person p = Person.getPersonById(userId);
    	if(p == null){
    		gResp.setMessage("Incorrect user id.");
    		res = Response.status(404).entity(gResp).build();
    		return res;
    	}
        List<Goal> goals = p.getGoals();
        gResp.setGoals(goals);
        res = Response.ok(gResp).build();
        return res;
    }

    @GET
    @Path("{userId}/runs")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRecentRuns(
    		@PathParam("userId") int userId, 
    		@DefaultValue("0") @QueryParam("start_date") long startDate) {
    	Response res;
    	RunsResponse rResp = new RunsResponse();
    	
    	//get Person
    	Person p = Person.getPersonById(userId);
    	Timestamp t = new Timestamp(startDate);
        
    	//person doesn't exist
    	if(p==null){
    		rResp.setMessage("Incorrect user id.");
    		return Response.status(404).entity(rResp).build();
    	}
    	
    	//try catch in case jpa throws some error
    	List<Run> runs = null;
    	try {
    		runs = p.getRecentRuns(t);
    	} catch(Exception e){
    		rResp.setMessage(e.getMessage());
    		return Response.status(404).entity(rResp).build();
    	}
   
    	rResp.setRuns(runs); //converts to output format
    	res = Response.ok(rResp).build(); //return 200 OK + runs object
    	
        return res;
    }
    
    @POST
    @Path("{userId}/runs")
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerRun(@PathParam("userId") int userId, InputRun iRun) {
    	Response res;
    	BasicResponse bResp = new BasicResponse();
    	
    	//Validate user exists
    	Person p = Person.getPersonById(userId);
    	if(p == null){
    		bResp.setMessage("Incorrect user id.");
    		return Response.status(404).entity(bResp).build();
    	}
    	
    	//Validate run parameters
    	if(iRun.getStart_date() == null){
    		bResp.setMessage("Must provide a start_date for the run.");
    		return Response.status(400).entity(bResp).build();
    	}
    	    	
    	//convert into model
    	Run nRun = iRun.toRun();
    	nRun.setPersonId(p.getId());
    	URI location = null;
    	try {
        	Run.saveRun(nRun);
    		location = new URI(uriInfo.getAbsolutePath().toString()+nRun.getId());
    	} catch (Exception e){
    		bResp.setMessage(e.getMessage());
    		return Response.status(404).entity(bResp).build();
    	}
    	res = Response.created(location).entity(nRun).build();
		return res;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> getAllPeople() {
        List<Person> people = Person.getAll();
        return people;
    }
  
}