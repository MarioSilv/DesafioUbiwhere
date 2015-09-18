/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.restAPI;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ubiwhere.example.desafio.dataBase.EntityDB;
import ubiwhere.example.desafio.manager.Manager;
import ubiwhere.example.desafio.utils.Errors;
import ubiwhere.example.desafio.utils.Responses;
import ubiwhere.example.desafio.utils.Utilities;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
@Path("Contacts/")
public class Contacts {

    @EJB
    Manager manager;
//list of contacts. com filtros
    static final Logger LOG = LoggerFactory.getLogger(Contacts.class);

    /**
     *
     * @param typeOfQuery
     * @param query
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContacts(@QueryParam("queryBy") String typeOfQuery, @QueryParam("query") String query) {
        if (typeOfQuery == null && query == null) {
            LOG.info("Get all contacts");
            List<EntityDB> result = manager.getAllEntities();
            return Responses.sendOkResponse(result);
        } else if (typeOfQuery != null && query != null && !query.equals("")) {
            List<EntityDB> result;
            switch (typeOfQuery) {
                case "name":
                    LOG.info("Get Contact by name: " + query);
                    result = manager.getEntitiesByName(query);
                    break;
                case "address":
                    LOG.info("Get Contact by address: " + query);
                    result = manager.getEntitiesByAddress(query);
                    break;
                case "location":
                    LOG.info("Get Contact by location: " + query);
                    result = manager.getEntitiesByLocation(query);
                    break;
                case "contact":
                    LOG.info("Get Contact by contact: " + query);
                    result = manager.getEntitiesByContact(query);
                    break;
                default:
                    LOG.info("QueryBy not found: ");
                    return Responses.errorParameterInvalid(Errors.InvalidParameters.toString());
            }
            if (result != null) {
                return Responses.sendOkResponse(result);
            }
        }
        return Responses.errorParameterMissing(Errors.ParametersMissing.toString());
    }

    /**
     *
     * @param idContact
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idContact}")
    public Response getContacts(@PathParam("idContact") int idContact) {
        EntityDB result = null;
        if (idContact > 0) {
            LOG.info("Get Entity by ID ");
            result = manager.getEntityByID(idContact);
        }
        if (result != null) {
            return Responses.sendOkResponse(result);
        } else {
            return Responses.errorParameterInvalid(Errors.InvalidParameters.toString());
        }

    }

    /**
     *
     * @param message
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContact(String message) {
        if (message != null && !message.equals("")) {
            LOG.info("ConvertEntity on addContact");
            EntityDB entity = convertStringToEntityDB(message);
            if (entity != null) {
                LOG.info("Saving Object");
                return Responses.sendOkResponse(manager.SaveContact(entity, entity.getContact(), entity.getAddress()));
            }
        }

        return Responses.errorBodyInvalid(Errors.InvalidBody.toString());

    }

    /**
     *
     * @param message
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editContact(String message) {
        if (message != null && !message.equals("")) {
            LOG.info("ConvertEntity on editContact");
            EntityDB entity = convertStringToEntityDB(message);
            if (entity != null) {
                LOG.info("Editing Object");
                return Responses.sendOkResponse(manager.EditContact(entity, entity.getContact(), entity.getAddress()));
            }
        }
        return Responses.errorBodyInvalid(Errors.InvalidBody.toString());

    }

    /**
     *
     * @param idContact
     * @return
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idContact}")
    public Response deleteContact(@PathParam("idContact") int idContact) {
        EntityDB result = null;
        if (idContact > 0) {
            LOG.info("Get Entity by ID ");
            result = manager.getEntityByID(idContact);
        }
        if (result != null) {
            LOG.info("Removing Object");
            return Responses.sendOkResponse(manager.RemoveContact(idContact));
        } else {
            return Responses.errorParameterInvalid(Errors.InvalidParameters.toString());
        }

    }

    /**
     *
     * @param body
     * @return
     */
    private static EntityDB convertStringToEntityDB(String body) {
        try {
            Class<?> c = Class.forName("ubiwhere.example.desafio.dataBase.EntityDB");
            Object data = Utilities.getGsonFactory().fromJson(body, c);
            return (EntityDB) data;
        } catch (ClassNotFoundException ex) {
            LOG.error("Error converting the message : " + ex.toString());
        }
        return null;
    }

}
