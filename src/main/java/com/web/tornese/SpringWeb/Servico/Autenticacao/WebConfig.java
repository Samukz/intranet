package com.web.tornese.SpringWeb.Servico.Autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registrar o interceptor para todas as rotas exceto '/login' e '/logar'
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/login", "/logar");
    }
}
