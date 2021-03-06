package fitbot.ldbs.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import fitbot.ldbs.dao.FitBotDao;

/**
 * This is the main entity and represents a Person
 * along with her measures
 */
@Entity
@Table(name="Person")
@NamedQueries(value={
		@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),

		@NamedQuery(name="Person.findBySlackId", query="SELECT p FROM Person p WHERE p.slack_user_id=:slack_user_id")
})
public class Person {

	@Id
	@SequenceGenerator(name="person_id_gen",
	sequenceName="person_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="person_id_gen")
	@Column(name="id", updatable=false)
	private int id;

	@Column(name="firstname")
	private  String firstname;

	@Column(name="lastname")
	private String lastname;

	@Column(name="email")
	private String email;

	@Column(name="strava_access_token")
	private String strava_access_token;

	@Column(name="strava_code")
	private String strava_code;

	@Column(name="telegram_user_id")
	private Integer telegram_user_id;

	@Column(name="telegram_chat_id")
	private Integer telegram_chat_id;

	@Column(name="slack_user_id")
	private String slack_user_id;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="person_id", referencedColumnName="id")
	private List<Run> runs;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="person_id", referencedColumnName="id")
	private List<Goal> goals;

	public Person(){

	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getStrava_access_token() {
		return strava_access_token;
	}

	public String getStrava_code() {
		return strava_code;
	}

	public Integer getTelegram_user_id() {
		return telegram_user_id;
	}

	public Integer getTelegram_chat_id() {
		return telegram_chat_id;
	}

	public String getSlack_user_id() {
		return slack_user_id;
	}
	
	public List<Run> getRuns() {
		return runs;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStrava_access_token(String strava_access_token) {
		this.strava_access_token = strava_access_token;
	}

	public void setStrava_code(String strava_code) {
		this.strava_code = strava_code;
	}

	public void setTelegram_user_id(Integer telegram_user_id) {
		this.telegram_user_id = telegram_user_id;
	}

	public void setTelegram_chat_id(Integer telegram_chat_id) {
		this.telegram_chat_id = telegram_chat_id;
	}

	public void setSlack_user_id(String slack_user_id) {
		this.slack_user_id = slack_user_id;
	}
	
	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
    
    /**
     * Retrieve a Person from the database by id
     * @param personId id of the Person
     * @return The Person if exists, else null
     */
    public static Person getPersonById(int personId) {
        EntityManager em = FitBotDao.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
        FitBotDao.instance.closeConnections(em);
        return p;
    }
    
	public static List<Person> getAll() {
        EntityManager em = FitBotDao.instance.createEntityManager();
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
        FitBotDao.instance.closeConnections(em);
        return list;
    }
	
    /**
     * Save a new Person to the database
     * @param p Person to be saved
     * @return Returns a copy of the Person object, with id set
     */
    public static Person savePerson(Person p) {
        EntityManager em = FitBotDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        FitBotDao.instance.closeConnections(em);
        return p;
    } 
    
    /**
     * Save an existing Person to the database
     * @param p Person to be saved
     * @return Return a copy of the Person object
     */
    public static Person updatePerson(Person p) {
        EntityManager em = FitBotDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        FitBotDao.instance.closeConnections(em);
        return p;
    }
    
    public static Person getPersonBySlackUserId(String slackUserId){
    	EntityManager em = FitBotDao.instance.createEntityManager();
    	List<Person> ps = em.createNamedQuery("Person.findBySlackId", Person.class)
    			.setParameter("slack_user_id", slackUserId)
    			.getResultList();
    	Person p = null;
    	if(!ps.isEmpty()){
    		p = ps.get(0);
    	}
    	FitBotDao.instance.closeConnections(em);
    	return p;
    }
    
    public List<Run> getRecentRuns(Timestamp startDate){
    	EntityManager em = FitBotDao.instance.createEntityManager();
    	List<Run> rs = em.createNamedQuery("Run.findRecentRuns", Run.class)
    			.setParameter("personId", getId())
    			.setParameter("startDate", startDate)
    			.getResultList();
    	FitBotDao.instance.closeConnections(em);
    	return rs;
    }
    
    public Goal setUserGoal(Goal g){
    	Goal prev = null;
    	EntityManager em = FitBotDao.instance.createEntityManager();
    	TypedQuery<Goal> q = em.createNamedQuery("Goal.findByUserAndType", Goal.class)
    			.setParameter("personId", getId())
    			.setParameter("goalType", g.getGoalType().getId());    			
    	List<Goal> res = q.getResultList();
    	if(!res.isEmpty()){
    		//copy new values into old
    		prev = res.get(0);
    		prev.setGoalPeriod(g.getGoalPeriod());
    		prev.setTargetValue(g.getTargetValue());    
    	} else {
    		//create new object
    		prev = g;
    		prev.setPersonId(getId());
    	}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		prev.setCreationDate(new Timestamp(calendar.getTimeInMillis()));
    	EntityTransaction tx = em.getTransaction();
        tx.begin();
        prev = em.merge(prev);
        tx.commit();
    	FitBotDao.instance.closeConnections(em);
    	return prev;
    }

}