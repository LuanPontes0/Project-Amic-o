package com.apiPetshop.apiPetshop.service.Autenticacao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.service.CookieSErvice;



@Component
public class LoginInterceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
      
      try{
         if(CookieSErvice.getCookie(request, "usuarioId") != null){
            return true;
         }
      }
      catch(Exception erro) {}
      
      response.sendRedirect("/");
      return false;
   }
}
