package com.web.tornese.SpringWeb.Servico.Autenticacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

import com.web.tornese.SpringWeb.Servico.CookieService;

@Component
public class LoginInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
      
      String usuarioId = CookieService.getCookie(request, "usuarioId");
      String tipoUsuario = CookieService.getCookie(request, "tipoUsuario"); // Novo cookie para tipo de usuário

      if(usuarioId != null){
         // Exemplo de checagem: se a rota é "/administradores" e o tipo de usuário não é "ADMIN", redirecione.
         if(request.getRequestURI().equals("/administradores") && !tipoUsuario.equals("ADMIN")){
            response.sendRedirect("/"); // Redirecione para a página inicial ou de erro
            return false;
         }
         return true; // Continua a execução normal para usuários autorizados
      }
      
      // Redireciona para o login se não houver cookie de usuário
      response.sendRedirect("/login");
      return false;
   }


}