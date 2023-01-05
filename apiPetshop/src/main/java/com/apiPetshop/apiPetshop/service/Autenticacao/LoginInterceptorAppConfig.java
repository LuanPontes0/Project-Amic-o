package com.apiPetshop.apiPetshop.service.Autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginInterceptorAppConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        .excludePathPatterns(
         "/",
         "/pesquisar-User",
         "/pesquisar-req",
         "/pesquisa-resultadoUserUser",
        
         "/login",
         
         
        
         
         "/loginUser",
         "/insertUsuarioAdm",
         "/inserirUsuarioAdm",
         "/inserirUsuario",
         "/inserirUsuarioSenha",
         "/insertUsuario",
         "/Pet/**",
         "/Usuario/**",
         "/img/**",
         "/js/**",
         "/css/**"
        );
  }
}
