package demo_backend.restcontroller;

import demo_backend.authentication.AuthenticationBean;
import demo_backend.entities.User;
import demo_backend.model.out.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthController {

    @Autowired
    private AuthenticationBean authenticationBean;

    /*
     Ricavo da RequestBody i parametri Utente e Password, uso il metodo authorize() di AuthorizatioBean
     per verificare se user e pwd sono corretti. Se authorize() ritorna null -> ritorno errore al frontend Angular (user/pwd non validi)
     se authorize torna un token non null -> ritorno il token al frontend Angular
    */
    @PostMapping(value = "admin/basicauth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserDTO userDTO) throws  Exception{
        User u= new User();
        u.setName(userDTO.getName());
        u.setPassword(userDTO.getPassword());

        String token= authenticationBean.authorize(u.getName(), u.getPassword());
        if(token==null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username e Password non validi!");
        }
        return "{ \"token\": \"" + token + "\"}"; //Al posto di ritornare solo il token si restituisce il JSON del token.
        //Non ritorniamo solo il valore, ma { "token": "<valore_del_token>" }
    }

}
