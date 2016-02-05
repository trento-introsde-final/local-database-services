package fitbot.ldbs.test.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fitbot.ldbs.model.Person;

public class PersonTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private EntityTransaction tx;
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing JPA on lifecoach database using 'fitbot-db' persistence unit");
        emf = Persistence.createEntityManagerFactory("fitbot-db");
        em = emf.createEntityManager();
        System.out.println("Created entity manager");
    }

    @AfterClass
    public static void afterClass() {
        em.close();
        emf.close();
    }

    @Before
    public void before() {
        tx = em.getTransaction();
    }
    
    @Test
    public void readPersonListTest() {
        System.out.println("--> TEST: readPersonList");
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
                .getResultList();
        for (Person person : list) {
            System.out.println("--> Person = "+person.toString());
        }
        assertTrue(list.size()>0);
    }
    




}
