package rest;

import com.google.gson.Gson;
import dbfacade.DataFacade;
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
@Path("car")
public class CarResource {

    DataFacade hf = new DataFacade();

    Gson gson = new Gson();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("availablecars/{week}/{address}")
    public String getCars(@PathParam("week") String week, @PathParam("address") String address) throws Exception {
        //EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//        History history = new History("week:" + week + ",address:" + address);
//        hf.addHistory(history);

//        String result = "[";
//        result += GetJson.getJsonTwo("avis", week, address) + ", ";
//        result += GetJson.getJsonTwo("hertz", week, address) + ", ";
//        result += GetJson.getJsonTwo("europcar", week, address) + ", ";
//        result += GetJson.getJsonTwo("bugdet", week, address) + ", ";
//        result += GetJson.getJsonTwo("alamo", week, address) + "]";
        String result = ParallelPinger.getJsonFromAllServers("week=" + week + "&addr=" + address).replaceAll("\\s+", "");

        return "[" + result + "]";

    }
}
