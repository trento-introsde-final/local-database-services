package fitbot.ldbs.rest;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fitbot.ldbs.model.GoalType;
import fitbot.ldbs.rest.output.GoalTypeResponse;
import fitbot.ldbs.rest.output.GoalTypesResponse;

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
    public Response getGoalTypes(){
    	List<GoalType> gts = GoalType.getAll();
    	GoalTypesResponse gtResp = new GoalTypesResponse();
    	gtResp.setResults(gts);
    	Response res = Response.ok(gtResp).build();
    	return res;
    }
    
    @GET
    @Path("{goalType}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGoalType(@PathParam("goalType") String goalType) {
    	GoalType gt = GoalType.getGoalTypeById(goalType);
    	GoalTypeResponse gtResp = new GoalTypeResponse();
    	Response res;
    	if(gt == null){
    		gtResp.setError("Incorrect goal type name.");
    		res = Response.status(404).entity(gtResp).build();
    		return res;
    	}
    	gtResp.setName(gt.getName());
    	gtResp.setId(gt.getId());
    	gtResp.setUnits(gt.getUnits());
    	res = Response.ok(gtResp).build();
        return res;
    }

}
