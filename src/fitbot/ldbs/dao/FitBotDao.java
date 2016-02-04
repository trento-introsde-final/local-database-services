package fitbot.ldbs.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum FitBotDao {

	instance;
	private EntityManagerFactory emf;
	
	private FitBotDao() {
	    if (emf!=null) {
	        emf.close();
	    }
	    System.out.println("Echo cho ho o  ...");
	    Map<String, Object> configOverrides = new HashMap<String, Object>();
	    String dbURL = System.getenv("DATABASE_URL");
	    try {
		    if(dbURL != null){
		    	URI dbUri = new URI(dbURL);
	    	    
		    	String username = dbUri.getUserInfo().split(":")[0];
	    	    String password = dbUri.getUserInfo().split(":")[1];
	    	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		    
	    	    configOverrides.put("javax.persistence.jdbc.url", dbUrl);
	    	    configOverrides.put("javax.persistence.jdbc.user", username);
	    	    configOverrides.put("javax.persistence.jdbc.password", password);
		    }
	    } catch (URISyntaxException e ){
	    	System.err.println("Could not parse DATABASE_URL. Invalid URI: dbURL");
	    }
	    
	    emf = Persistence.createEntityManagerFactory("fitbot-db", configOverrides);
	}
	
	public EntityManager createEntityManager() {
	    try {
	        return emf.createEntityManager();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;    
	}
	
	public void closeConnections(EntityManager em) {
	    em.close();
	}
	
	public EntityTransaction getTransaction(EntityManager em) {
	    return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
	    return emf;
	}  
}
