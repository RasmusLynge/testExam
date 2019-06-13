/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.History;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Rasmu
 */
public class HistoryFacade {
    EntityManagerFactory emf;

    public HistoryFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public History addHistory(History history) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(history);
            em.getTransaction().commit();
            return history;
        } finally {
            em.close();
        }
    }
}
