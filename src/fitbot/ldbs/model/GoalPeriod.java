package fitbot.ldbs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import fitbot.ldbs.dao.FitBotDao;

@Entity
@Table(name="Goal_Period")
@NamedQueries(value={
		@NamedQuery(name="GoalPeriod.findByName", query="SELECT gp FROM GoalPeriod gp WHERE gp.name=:name")
})
public class GoalPeriod {

	@Id
	@Column(name="days_length") 
	private int daysLength; 
	
	@Column(name="name", unique=true)
	private String name;
	
	public GoalPeriod(){
		
	}

	public int getDaysLength() {
		return daysLength;
	}

	public String getName() {
		return name;
	}

	public void setDaysLength(int daysLength) {
		this.daysLength = daysLength;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Retrieve a GoalPeriod from the database 
     * @param personId id of the Person
     * @return The Person if exists, else null
     */
    public static GoalPeriod getGoalPeriodByName(String name) {
    	EntityManager em = FitBotDao.instance.createEntityManager();
    	TypedQuery<GoalPeriod> gp = em.createNamedQuery("GoalPeriod.findByName", GoalPeriod.class)
    			.setParameter("name", name);		
    	List<GoalPeriod> res = gp.getResultList();
    	FitBotDao.instance.closeConnections(em);
        
    	if (res.isEmpty()){
    		return null;
    	}
    	return res.get(0);
    }
}
