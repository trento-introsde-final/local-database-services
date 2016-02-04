package fitbot.ldbs.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Run_History")
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
	private Date startdate;
	
	@Column(name="moving_time")
	private Float movingTime;
	
	@Column(name="elevation_gain")
	private Float elevationGain;
	
	@Column(name="max_speed")
	private Float maxSpeed;
	
	@Column(name="avg_speed")
	private Float avgSpeed;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

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

	public Date getStartdate() {
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

	public void setId(int id) {
		this.id = id;
	}


	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public void setCalories(Float calories) {
		this.calories = calories;
	}

	public void setStartdate(Date startdate) {
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
	
	
}
