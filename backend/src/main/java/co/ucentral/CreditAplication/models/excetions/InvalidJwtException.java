package co.ucentral.CreditAplication.models.excetions;

public class InvalidJwtException extends Exception{

    public InvalidJwtException(String usernameAlreadyExists) {
        super(usernameAlreadyExists);
    }
}
