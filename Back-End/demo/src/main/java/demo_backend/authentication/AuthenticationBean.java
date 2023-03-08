package demo_backend.authentication;

import demo_backend.entities.User;
import demo_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationBean implements AuthInterface {

    @Autowired
    private UserRepository userRepository;
    private String message;

    /*
     * mappa <key, value> dove key è di tipo string e value è di tipo java.util.Date
     * key-> token di autorizzazione
     * value-> timestamp di ultima richiesta
     * cerca il javadoc di java.util.Map
     */
    Map<String, Date> authorizedUsers = new HashMap<String, Date>();



    /*
    Questo metodo controlla (con query) sul db se utente e password matchano
    se matchano -> genera un token che viene salvato con timestamp = questo istante
    nella mappa authorizedUsers e ritorna il token
    se non matchano -> return null
    */

    @Override
    public String authorize(String name, String pwd) {
        User user = userRepository.findUserByName(name);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(pwd)) {
            return null;
        }
        String token = name + ":" + pwd;
        Date date = new Date();
        authorizedUsers.put(token, date);
        System.out.println(authorizedUsers);
        return token;

    }

    /*
     * Controlla nella mappa se authorizedUsers se 1) il token esiste e 2) il
     * suo timestamp non è trascorso da più di 1 ora
     * se la condizione di cui sopra e' vera -> ritorna true (autorizzato).
     *
     * Se la condizione di cui sopra è falsa -> ritorna false (non autorizzato)
     */

    @Override
    public boolean isAuthorized(String token) {
        System.out.println(token);
        System.out.println(authorizedUsers.containsKey(token));
        if (authorizedUsers.containsKey(token)) {
            System.out.println(authorizedUsers+"success");
            Date date = authorizedUsers.get(token);
            Date date2 = new Date();
            if (date.getTime() - date2.getTime() < 3600000) {
                authorizedUsers.put(token, date2);
                return true;
            }
        }
        System.out.println(authorizedUsers+"fail");
        authorizedUsers.remove(token);
        return false;
    }

    public AuthenticationBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
}
