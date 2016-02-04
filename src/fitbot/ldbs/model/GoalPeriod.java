package fitbot.ldbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
}
