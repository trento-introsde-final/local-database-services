package fitbot.ldbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Goal_Measure_Type")
public class GoalType {

	@Id
	@SequenceGenerator(name="goal_measure_type_id_seq",
	sequenceName="goal_measure_type_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="goal_measure_type_id_seq")
	@Column(name="id", updatable=false)
	private int id;
	
	@Column(name="units", unique=true)
	private String units;
	
	@Column(name="name", unique=true)
	private String name;
	
	public GoalType(){
		
	}

	public int getId() {
		return id;
	}

	public String getUnits() {
		return units;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
