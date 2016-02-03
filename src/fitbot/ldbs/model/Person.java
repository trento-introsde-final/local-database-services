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

/**
 * This is the main entity and represents a Person
 * along with her measures
 */
@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person {

	@Id
	@SequenceGenerator(name="person_id_seq",
	sequenceName="person_id_seq",
	allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="person_id_seq")
	@Column(name="id", updatable=false)
	private Long id;

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

	public Person(){

	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setId(Long id) {
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
	
	public static List<Person> getAll() {
        EntityManager em = FitBotDao.instance.createEntityManager();
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
        FitBotDao.instance.closeConnections(em);
        return list;
    }

}