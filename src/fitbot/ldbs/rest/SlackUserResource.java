package fitbot.ldbs.rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.Person;

@Stateless
@LocalBean
public class SlackUserResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public SlackUserResource(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }
    

    @GET
    @Path("{slackUserId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserBySlackId(@PathParam("slackUserId") String slackUserId){
    	Response res;
    	Person person = Person.getPersonBySlackUserId(slackUserId);
    	res = Response.ok(person).build();
    	return res;
    }
  
}