package fitbot.ldbs.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.Goal;
import fitbot.ldbs.model.Person;
import fitbot.ldbs.model.Run;
import fitbot.ldbs.rest.input.SimpleDateInput;


@Stateless
@LocalBean
public class UserResource {
    
	@Context
    UriInfo uriInfo;
    @Context
    Request request;

    public UserResource(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
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
    public List<Run> getRecentRuns(@PathParam("userId") int userId, SimpleDateInput since) {
    	Person p = Person.getPersonById(userId);
    	Timestamp t = new Timestamp(since.getStartDate());
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
    	try {
    		location = new URI(uriInfo.getAbsolutePath().toString()+"/"+p.getId()+"/runs/"+nRun.getId());
    	} catch (URISyntaxException e){
    	}
    	res = Response.created(location).entity(nRun).build();
		return res;
    }
  
}