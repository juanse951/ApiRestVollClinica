package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired //siempre es mejor a nivel de constructor
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        if(token == null || token == ""){
            throw new RuntimeException("El token enviado no es valido");
        }
        token = token.replace("Bearer ","");
        System.out.println(token);
        System.out.println(tokenService.getSubject(token));//este usuario tiene sesion?
        filterChain.doFilter(request,response);// unica forma de llamar al filtro
    }
}
