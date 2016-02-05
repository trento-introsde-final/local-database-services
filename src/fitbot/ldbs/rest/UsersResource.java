package fitbot.ldbs.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    public Response newPerson(Person person) throws IOException {
    	Response res;
    	person = Person.savePerson(person);
    	URI location = null;
    	try {
    		location = new URI(uriInfo.getAbsolutePath().toString()+"/"+person.getId());
    	} catch (URISyntaxException e){
    	}
    	res = Response.created(location).entity(person).build();
		return res;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{userId}/goals/{goalType}")
    public Response setPersonalGoal(
    		@PathParam("userId") int userId, 
    		@PathParam("goalType") String goalType, Goal g){
    	Response res;
    	GoalType gType = GoalType.getGoalTypeById(goalType);//TODO: Check not null
    	g.setGoalType(gType);
    	Person.setUserGoal(userId, g);
    	res = Response.ok().build();
    	return res;
    }

    @GET
    @Path("{userId}/goals")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Goal> getPersonalGoals(@PathParam("userId") int userId) {
    	Person p = Person.getPersonById(userId);
        List<Goal> goals = p.getGoals();
        return goals;
    }

    @GET
    @Path("{userId}/runs")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Run> getRecentRuns(
    		@PathParam("userId") int userId, 
    		@DefaultValue("0") @QueryParam("start_date") long startDate) {
    	Person p = Person.getPersonById(userId);
    	Timestamp t = new Timestamp(startDate);
        List<Run> runs = p.getRecentRuns(t);
        return runs;
    }
    
    @POST
    @Path("{userId}/runs")
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerRun(@PathParam("userId") int userId, Run nRun) {
    	Response res;
    	Person p = Person.getPersonById(userId);
    	nRun.setPersonId(p.getId());
    	URI location = null;
    	Run.saveRun(nRun);
    	try {
    		location = new URI(uriInfo.getAbsolutePath().toString()+"/"+nRun.getId());
    	} catch (URISyntaxException e){
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