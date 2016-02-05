package fitbot.ldbs.rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("/")
public class Dispatcher {
	 @Context
	    UriInfo uriInfo;
	    @Context
	    Request request;

	    @PersistenceUnit(unitName="fitbot-db")
	    EntityManager entityManager;

	    @PersistenceContext(unitName = "fitbot-db",type=PersistenceContextType.TRANSACTION)
	    private EntityManagerFactory entityManagerFactory;

	    @GET
	    @Path("hola")
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getHola() {
	        return "Hola";
	    }

	    @Path("goal-types")
	    public GoalResource routeGoal() {
	        return new GoalResource(uriInfo, request);
	    }
	    
	    @Path("users")
	    public UsersResource routeUserCollection() {
	        return new UsersResource(uriInfo, request);
	    }
	    
	    @Path("user-id")
	    public SlackUserResource routeUserId() {
	        return new SlackUserResource(uriInfo, request);
	    }
}
