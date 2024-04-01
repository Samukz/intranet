package com.web.tornese.SpringWeb.Servico.Autenticacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.web.tornese.SpringWeb.Servico.CookieService;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Verificar se o cookie de autenticação existe
        String usuarioId = CookieService.getCookie(request, "usuarioId");
        if (usuarioId == null || usuarioId.isEmpty()) {
            // Se não estiver logado, redirecionar para a página de login
            response.sendRedirect("/login");
            return false; // Não prossegue com a requisição original
        }
        return true; // Prossegue com a requisição original
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Implementação opcional
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Implementação opcional
    }
}
