package demo_backend.authentication;

public interface AuthInterface {

    public String authorize(String user, String pwd);

    public boolean isAuthorized(String token);


}
