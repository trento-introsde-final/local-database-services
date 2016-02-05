package fitbot.ldbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;

import fitbot.ldbs.dao.FitBotDao;

@Entity
@Table(name="Goal_Period")
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
        GoalPeriod gp = em.find(GoalPeriod.class, name);
        FitBotDao.instance.closeConnections(em);
        return gp;
    }
}
