package com.web.tornese.SpringWeb.Servico.Autenticacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.tornese.SpringWeb.Servico.CookieService;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String usuarioId = CookieService.getCookie(request, "usuarioId");
        // Se estiver tentando acessar uma página que não seja /login e não tiver o
        // cookie, redireciona para /login
        if (usuarioId == null && !request.getRequestURI().endsWith("/login")) {
            response.sendRedirect("/login");
            return false;
        }

        // Se o usuário tentar acessar "/administradores" sem ser ADMIN, redirecione
        String tipoUsuario = CookieService.getCookie(request, "tipoUsuario");
        if ("/administradores".equals(request.getRequestURI()) && !"ADMIN".equals(tipoUsuario)) {
            response.sendRedirect("/"); // Para página inicial ou de erro
            return false;
        }

        return true; // Continua a execução normal para usuários autenticados ou acessando /login
    }
}
