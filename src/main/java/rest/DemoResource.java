package rest;

import DTO.OwnerDTO;
import com.google.gson.Gson;
import dbfacade.DataFacade;
import entity.Car;
import entity.Owner;
import fetch.ParallelPinger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.PuSelector;

/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {

    DataFacade df = new DataFacade();

    Gson gson = new Gson();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello exam opgave 9\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getall")
    public String getCars(@PathParam("week") String week, @PathParam("address") String address) throws Exception {
        //List<Owner> owners = df.getAllOwners();
        List<Car> cars = df.getAllCars();
        return cars.toString();
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("history")
    @RolesAllowed("admin")
    public String showHistory() throws Exception {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//        List<History> histories = new ArrayList();
        try {
 //           histories = em.createQuery("select history from History history").getResultList();
            return gson.toJson("hej");
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() throws Exception {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
        String thisUser = securityContext.getUserPrincipal().getName();

        return "{\"name\":\"" + thisUser + "\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/wishpost/{wish}")
    @RolesAllowed("user")
    public void postUserWish(@PathParam("wish") String wish) {
//        EntityManager em1 = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//        String thisUser = securityContext.getUserPrincipal().getName();
//        User user = new User(thisUser, "");
//        Wish objectWish = new Wish(wish, user);
//        List isInDatabase = em1.createQuery("select wish from Wish wish where wish.wishID = :wishID ").setParameter("wishID", wish).getResultList();
//
//        if (isInDatabase.size() > 0) {
//            System.out.println("ALLEREDE I DATABASE ERRORROROROROROO");
//        } else {
//            try {
//                em.getTransaction().begin();
//                em.persist(objectWish);
//
//            } finally {
//                em.getTransaction().commit();
//                em.close();
//            }
//        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/wishdelete/{wish}")
    @RolesAllowed("user")
    public void deleteUserWish(@PathParam("wish") String wish) {
//        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//        String thisUser = securityContext.getUserPrincipal().getName();
//        User user = new User(thisUser, "");
//
//        try {
////            em.createQuery("DELETE FROM Wish wish WHERE wish.user.userName = :userName AND wish.wishID = :wishID ")
////                    .setParameter("wishID", wish).setParameter("userName", thisUser);
//            Wish w = (Wish) em.createQuery("select wish from Wish wish where wish.user.userName = :userName and wish.wishID = :wishID")
//                    .setParameter("wishID", wish).setParameter("userName", thisUser).getSingleResult();
//            System.out.println("TEEESSSSTT: " + w);
//            em.getTransaction().begin();
//            em.remove(w);
//            return wish;
//        } finally {
//            em.getTransaction().commit();
//            em.close();
//        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisUser = securityContext.getUserPrincipal().getName();

        return "{\"name\":\"" + thisUser + "\"}";
    }
}
