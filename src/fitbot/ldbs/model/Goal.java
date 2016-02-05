package fitbot.ldbs.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Goal")
@NamedQueries(value = {
	@NamedQuery(name="Goal.findByUserAndType",
				query="SELECT g FROM Goal g WHERE g.personId=:personId AND g.goalType.id=:goalType")
}
)
public class Goal {

	@Id
	@SequenceGenerator(name="goal_id_seq",
	sequenceName="goal_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="goal_id_seq")
	@Column(name="id", updatable=false)
	private int id;
	
	@Column(name="target_value")
	private Float targetValue;
	
	@Column(name="creation_date")
	private Timestamp creationDate;
	
	@ManyToOne
	@JoinColumn(name="goal_measure_type",referencedColumnName="id")
	private GoalType goalType;
	
	@ManyToOne
	@JoinColumn(name="goal_period",referencedColumnName="days_length")
	private GoalPeriod goalPeriod;

	@Column(name="person_id")
	private int personId;
	
	public Goal(){
		
	}

	public int getId() {
		return id;
	}

	public Float getTargetValue() {
		return targetValue;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public GoalPeriod getGoalPeriod() {
		return goalPeriod;
	}

	public int getPersonId() {
		return personId;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setTargetValue(Float targetValue) {
		this.targetValue = targetValue;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}

	public void setGoalPeriod(GoalPeriod goalPeriod) {
		this.goalPeriod = goalPeriod;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	
}
