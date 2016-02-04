package fitbot.ldbs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fitbot.ldbs.dao.FitBotDao;

@Entity
@Table(name="Goal_Measure_Type")
@NamedQuery(name="GoalType.findAll", query="SELECT gt FROM GoalType gt")
public class GoalType {

	@Id
	@SequenceGenerator(name="goal_measure_type_id_seq",
	sequenceName="goal_measure_type_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="goal_measure_type_id_seq")
	@Column(name="id", updatable=false)
	private String id;
	
	@Column(name="units")
	private String units;
	
	@Column(name="name", unique=true)
	private String name;
	
	public GoalType(){
		
	}

	public String getId() {
		return id;
	}

	public String getUnits() {
		return units;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Get all GoalTypes
     * @return
     */
    public static List<GoalType> getAll() {
        EntityManager em = FitBotDao.instance.createEntityManager();
        List<GoalType> list = em.createNamedQuery("GoalType.findAll", GoalType.class)
            .getResultList();
        FitBotDao.instance.closeConnections(em);
        return list;
    }
    
    /**
     * Retrieve a GoalType from the database 
     * @param personId id of the Person
     * @return The Person if exists, else null
     */
    public static GoalType getGoalTypeById(String id) {
        EntityManager em = FitBotDao.instance.createEntityManager();
        GoalType gt = em.find(GoalType.class, id);
        FitBotDao.instance.closeConnections(em);
        return gt;
    }
	
}
