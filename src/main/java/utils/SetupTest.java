package utils;

import entity.History;
import entity.Role;
import entity.User;
import javax.persistence.EntityManager;

public class SetupTest {

    public static void main(String[] args) {

        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        //throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
        em.getTransaction().begin();
        History history = new History("test");
        em.persist(history);
        em.getTransaction().commit();

    }

}
