package br.com.portifolio.todolist.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.portifolio.todolist.dao.IUserDAO;
import br.com.portifolio.todolist.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserDAO dao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/create" ) || servletPath.equals("/tasks/listTaskUser")) {

            // Pegar a autenticação (informação de usuario e senha)
            String auth = request.getHeader("Authorization");
            String authCut = auth.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authCut);

            String authString = new String(authDecode);
            String[] credentials = authString.split(":"); // cria um array [username, password]
            String username = credentials[0];
            String password = credentials[1];

            /*
             * System.out.println(auth);
             * System.out.println(authCut);
             * System.out.println(authDecode);
             * System.out.println(authString);
             * System.out.println(username);
             * System.out.println(password);
             */

            // Validar usuário
            User user = dao.findByUserName(username);
            if (user != null) {
                // Verificar a senha
                boolean passwordMatch = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified;
                if (passwordMatch) {
                    // Se a senha for válida, seta-a na tarefa.
                    request.setAttribute("idUser", user.getId());
                    System.out.println(">>>>>>>>>>>>>>>>> Id NO FILTRO: " + user.getId());
                    //Segue em diante
                    filterChain.doFilter(request, response);
                    System.out.println(">>>>>>>>>>>>>>>>> REQUEST: " + request.toString());
                } else {
                    response.sendError(401, "Senha incorreta!");
                }
            } else {
                response.sendError(401, "Usuário não encontrado!");
            }

        } else {
            // Segue em diante
            System.out.println("Segue o baile!");
            filterChain.doFilter(request, response);
        }
    }
}
