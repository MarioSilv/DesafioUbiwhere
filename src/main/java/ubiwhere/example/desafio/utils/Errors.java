package ubiwhere.example.desafio.utils;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public enum Errors {

    InvalidBody, ParametersMissing, InvalidParameters;

    @Override
    public String toString() {
        switch (this) {
            case InvalidBody:
                return "Invalid Body";
            case ParametersMissing:
                return "Parameters Missing";
            case InvalidParameters:
                return "Invalid Parameter(s)";

            default:
                return super.toString();
        }
    }

}
