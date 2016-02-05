package fitbot.ldbs.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fitbot.ldbs.dao.FitBotDao;

@Entity
@Table(name="Run_History")
@NamedQueries(value={
		@NamedQuery(name="Run.findRecentRuns", 
					query="SELECT r FROM Run r "
						 +"WHERE r.startdate>=:startDate "
						 +"AND r.personId=:personId"),
})
public class Run {

	@Id
	@SequenceGenerator(name="run_history_id_seq",
	sequenceName="run_history_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="run_history_id_seq")
	@Column(name="id", updatable=false)
	private int id;
	
	@Column(name="distance")
	private Float distance;
	
	@Column(name="calories")
	private Float calories;
	
	@Column(name="startdate")
	private Timestamp startdate;
	
	@Column(name="moving_time")
	private Float movingTime;
	
	@Column(name="elevation_gain")
	private Float elevationGain;
	
	@Column(name="max_speed")
	private Float maxSpeed;
	
	@Column(name="avg_speed")
	private Float avgSpeed;
	
	@Column(name="person_id")
	private int personId;
	
	@Column(name="steps")
	private int steps;

	public Run(){
		
	}

	public int getId() {
		return id;
	}


	public Float getDistance() {
		return distance;
	}

	public Float getCalories() {
		return calories;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public Float getMovingTime() {
		return movingTime;
	}

	public Float getElevationGain() {
		return elevationGain;
	}

	public Float getMaxSpeed() {
		return maxSpeed;
	}

	public Float getAvgSpeed() {
		return avgSpeed;
	}

	public int getSteps() {
		return steps;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public void setCalories(Float calories) {
		this.calories = calories;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public void setMovingTime(Float movingTime) {
		this.movingTime = movingTime;
	}

	public void setElevationGain(Float elevationGain) {
		this.elevationGain = elevationGain;
	}

	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setAvgSpeed(Float avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
    /**
     * Save a new Run to the database
     * @param r Run to be saved
     * @return Returns a copy of the Run object, with id set
     */
    public static Run saveRun(Run r) {
        EntityManager em = FitBotDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(r);
        tx.commit();
        FitBotDao.instance.closeConnections(em);
        return r;
    } 
	
}
