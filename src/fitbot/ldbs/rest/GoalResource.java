package fitbot.ldbs.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.GoalType;

@Stateless
@LocalBean
public class GoalResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public GoalResource(UriInfo uriInfo, Request request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> getGoalTypes(){
    	List<GoalType> gts = GoalType.getAll();
    	List<String> types = new ArrayList<String>();
    	for(GoalType gt: gts){
    		types.add(gt.getId());
    	}
    	return types;
    }
    
    @GET
    @Path("{goalType}")
    @Produces({MediaType.APPLICATION_JSON})
    public GoalType getGoalType(@PathParam("goalType") String goalType) {
    	GoalType gt = GoalType.getGoalTypeById(goalType);
        return gt;
    }

}
