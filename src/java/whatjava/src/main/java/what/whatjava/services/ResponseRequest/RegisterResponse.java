package what.whatjava.services.ResponseRequest;

public class RegisterResponse {

    String name;
    

    public static String from(String nameUser){

        String message = "The user" + nameUser + "got created";

        return message;
    }
}
