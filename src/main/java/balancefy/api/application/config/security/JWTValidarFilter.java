package balancefy.api.application.config.security;

import balancefy.api.resources.entities.DetalheUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidarFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsuarioRepository repository;

    public JWTValidarFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = receperarToken(request);
        boolean valido = tokenService.isTokenValido(token);

        if(valido) {
            autenticarCliente(token);
        }

        chain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        int idUsuario = tokenService.getIdUsuario(token);

        Usuario usuario= repository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String receperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
