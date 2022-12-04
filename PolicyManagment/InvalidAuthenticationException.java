package PolicyManagment;

public class InvalidAuthenticationException extends Throwable{
    public InvalidAuthenticationException(String message){
        super(message);
        System.out.println("Wrong password!");
    }
}
