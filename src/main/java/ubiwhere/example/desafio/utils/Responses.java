package ubiwhere.example.desafio.utils;

import com.google.gson.annotations.Expose;
import javax.ws.rs.core.Response;
import static ubiwhere.example.desafio.utils.Utilities.getGsonFactory;

/**
 * Class that has the information about Response of the REST API.
 * Access-Control-Allow-Origin to * and response codes appropriated.
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public class Responses {

    @Expose
    private final String errorMessage;
    @Expose
    private final String cause;
    @Expose
    private final String error;

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorParameterMissing(String cause) {
        Responses error = new Responses("Parameters missing", cause, "1");
        return Response.status(412).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorParameterInvalid(String cause) {
        Responses error = new Responses("Invalid Parameter", cause, "2");
        return Response.status(412).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorFailCreation(String cause) {
        Responses error = new Responses("The creation fail", cause, "3");
        return Response.status(400).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    public static Response error(String cause) {
        Responses error = new Responses("Something happens", cause, "7");
        return Response.status(400).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorBodyInvalid(String cause) {
        Responses error = new Responses("The information on body is invalid", cause, "4");
        return Response.status(412).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorObjectAlreadyExists(String cause) {
        Responses error = new Responses("The Object already exist on this system", cause, "5");
        return Response.status(404).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param cause
     * @return
     */
    public static Response errorObjectNotExists(String cause) {
        Responses error = new Responses("The Object don't exist on this system", cause, "6");
        return Response.status(404).header("Access-Control-Allow-Origin", "*").entity(error.toJson()).build();
    }

    /**
     *
     * @param resultClass
     * @return
     */
    public static Response sendOkResponse(Object resultClass) {
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(getGsonFactory().toJson(resultClass)).build();
    }

    /**
     *
     * @param errorMessage
     * @param cause
     * @param error
     */
    public Responses(String errorMessage, String cause, String error) {
        this.errorMessage = errorMessage;
        this.cause = cause;
        this.error = error;
    }

    /**
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @return
     */
    public String getCause() {
        return cause;
    }

    /**
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ErrorCodes{" + "errorMessage=" + errorMessage + ", cause=" + cause + ", error=" + error + '}';
    }

    /**
     *
     * @return
     */
    public String toJson() {
        return Utilities.getGsonFactory().toJson(this);
    }

}
