package fitbot.ldbs.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.Goal;
import fitbot.ldbs.model.GoalType;
import fitbot.ldbs.model.Person;


@Stateless
@LocalBean
public class UserCollectionResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public UserCollectionResource(UriInfo uriInfo, Request request) {
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
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> getAllPeople() {
        List<Person> people = Person.getAll();
        return people;
    }

  
}