package dbfacade;

import DTO.OwnerDTO;
import entity.Car;
import entity.Owner;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import utils.PuSelector;

/**
 *
 * @author Rasmu
 */
public class DataFacade {

    EntityManager em;

    public DataFacade() {
        this.em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
    }

    public Car addCar(Car car) {
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            return car;
        } finally {
            em.close();
        }
    }

    public Owner addOwner(Owner owner) {
        try {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
            return owner;
        } finally {
            em.close();
        }
    }

    public List<Car> getAllCars() {
        try {
            return (List<Car>) em.createQuery("select m from Car m").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Owner> getAllOwners() {
        try {
            return (List<Owner>) em.createQuery("select m from Owner m").getResultList();
        } finally {
            em.close();
        }
    }

    public Owner getOwner(Owner owner) {
        try {
            return (Owner) em.createQuery("select m from Owner m where id = :id").setParameter("id", owner.getId()).getSingleResult();
        } finally {
            em.close();
        }
    }

    public Car getCar(Car car) {
        try {
            return (Car) em.createQuery("select m from Car m where id = :id").setParameter("id", car.getId()).getSingleResult();
        } finally {
            em.close();
        }
    }

    public void deleteOwner(Owner owner) {
        Owner ownerToDelete = getOwner(owner);
        try {
            em.remove(ownerToDelete);
        } finally {
            em.close();
        }
    }

    public void deleteCar(Car car) {
        Car carToDelete = getCar(car);
        try {
            em.remove(carToDelete);
        } finally {
            em.close();
        }
    }
    
        public List<OwnerDTO> getAllPetDTO() {
        try {
            Query q = em.createQuery("SELECT NEW dto.OwnerDTO(p.id, p., p.birth, p.species, o.firstName, o.lastName) FROM Car p, Owner o WHERE p.owner.id = o.id");
            return (List<OwnerDTO>) q.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        DataFacade cf = new DataFacade();
        cf.addCar(new Car("test", "test", 2000, "test"));
        //System.out.println(cf.getAllCars());

    }
}
