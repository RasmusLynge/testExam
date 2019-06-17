package utils;

import entity.Car;
import entity.Owner;
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
//        Role userRole = new Role("user");
//        Role adminRole = new Role("admin");
//        User user = new User("user", "userpw");
//        user.addRole(userRole);
//        User admin = new User("admin", "adminpw");
//        admin.addRole(adminRole);
//        em.persist(userRole);
//        em.persist(adminRole);
//        em.persist(user);
//        em.persist(admin);
        Car car1 = new Car("polo", "ww", 1995, "green");
        Car car2 = new Car("polo", "ww", 1995, "Blue");
        Owner owner1 = new Owner("Lars", "Larsen", "club?");
        car1.setOwner(owner1);
        car2.setOwner(owner1);
        Owner owner2 = new Owner("Thomas", "Larsen", "club?");
        Car car3 = new Car("V18", "Volvo", 1995, "Blue");
        car3.setOwner(owner2);
        em.persist(car1);
        em.persist(car2);
        em.persist(owner1);
        em.persist(car3);
        em.persist(owner2);
        em.getTransaction().commit();

    }

}
