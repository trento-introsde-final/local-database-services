package fitbot.ldbs.rest;

import java.util.ArrayList;
import java.util.List;

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

import fitbot.ldbs.model.Person;

@Stateless
@LocalBean
@Path("/person")
public class Endpoint {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @PersistenceUnit(unitName="fitbot-db")
    EntityManager entityManager;

    @PersistenceContext(unitName = "fitbot-db",type=PersistenceContextType.TRANSACTION)
    private EntityManagerFactory entityManagerFactory;

    /**
     * @return  Returns the list of all People
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public List<Person> getPersonsBrowser() {
        List<Person> people = Person.getAll();
        return people;
    }

    /**
     * @return Returns the number of People records
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        System.out.println("Getting count...");
        List<Person> people = Person.getAll();
        int count = people.size();
        return String.valueOf(count);
    }

    @GET
    @Path("hola")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHola() {
        return "Hola";
    }

        
    

}