package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired //siempre es mejor a nivel de constructor
    private TokenService tokenService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener e token del header
        var AuthHeader = request.getHeader("Authorization");
        if(AuthHeader != null){
            var token = AuthHeader.replace("Bearer ","");
            var nombreDeUsuario = tokenService.getSubject(token); //obtenemos el subject
            if(nombreDeUsuario != null){
                //Token es valido
                var usuario = usuariosRepository.findByLogin(nombreDeUsuario); // encontramos al usuario
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); //autentificamos el usuario,forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);//Seteamos manualmente la autenticacion
            }
        }
        filterChain.doFilter(request,response);// unica forma de llamar al filtro
    }
}
