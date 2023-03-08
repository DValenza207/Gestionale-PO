package demo_backend.configuration;

import demo_backend.authentication.AuthInterface;
import demo_backend.authentication.AuthenticationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MinimalInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthInterface authInterface;

    /*
     Ricavo il token dall'HTTP header AUTHORIZATION dove deve esserci il valore "Bearer" + token.
     Uso questo token per invocare il metodo isAuthorized(token)
     se isAuthorized ritorna true -> interceptor ritorna true
     se isAuthorized ritorna false -> interceptor ritorna false (per ora teniamo il ritorna di dati vuoti al frontend, poi vediamo
     come si fa per gestire un eccezione che venga visualizzata)
     */
    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {

        System.out.println(requestServlet.getServletPath());
        System.out.println(requestServlet.getHeader(HttpHeaders.AUTHORIZATION));
        System.out.println("MINIMAL: INTERCEPTOR PREHANDLE CALLED");
        if (requestServlet.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }
        //error è per evitare che in caso di errore in login vada comunque a cercare il token
        if(requestServlet.getServletPath().contains("basicauth")||requestServlet.getServletPath().contains("error")){
            return  true;
        }
        String token = requestServlet.getHeader("Authorization").split(" ")[1]; //per escludere "Bearer " e quindi ottenere il vero token

        if (!authInterface.isAuthorized(token)) {
            System.out.println("not authorized");
            System.out.println(token);
            responseServlet.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        System.out.println("authorized");
        return authInterface.isAuthorized(token);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR POSTHANDLE CALLED");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR AFTERCOMPLETION CALLED");
    }

}
