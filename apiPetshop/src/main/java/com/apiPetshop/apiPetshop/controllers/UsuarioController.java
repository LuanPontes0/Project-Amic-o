package com.apiPetshop.apiPetshop.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.dao.AdmDao;
import com.apiPetshop.apiPetshop.dao.UsuarioDao;
import com.apiPetshop.apiPetshop.model.AdmModel;
import com.apiPetshop.apiPetshop.model.PetModel;
import com.apiPetshop.apiPetshop.model.UsuarioModel;
import com.apiPetshop.apiPetshop.service.CookieSErvice;
import com.apiPetshop.apiPetshop.service.ServiceAdm;
import com.apiPetshop.apiPetshop.service.ServiceUsuario;
import com.apiPetshop.apiPetshop.util.Util;

import Execeptions.ServiceExc;

@Controller
public class UsuarioController {
    @Autowired
    private ServiceAdm serviceAdm;
    @Autowired
    private AdmDao admrepositorio;
    @Autowired
    private UsuarioDao usuariorepositorio;
    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/loginUser");
        mv.addObject("usuario", new UsuarioModel());
        return mv;
    }
    @GetMapping("/login")
    public ModelAndView loginUser(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("adm", new AdmModel());
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("pet", new PetModel());/* msg definida para requisição no front */
        return mv;
    }
    @GetMapping("/indexUser")
    public ModelAndView indexUser() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/indexUser");
        mv.addObject("pet", new PetModel());/* msg definida para requisição no front */
        return mv;
    }
   


    @GetMapping("/inserirUsuario")
    public ModelAndView inserirtUsuario(UsuarioModel usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Usuario/formUsuario");
        mv.addObject("usuario", new UsuarioModel());
        return mv;
    }
    @GetMapping("/inserirUsuarioAdm")
    public ModelAndView inserirtUsuarioAdm(AdmModel adm) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Usuario/formUsuarioAdm");
        mv.addObject("adm", new AdmModel());
        return mv;
    }

    @PostMapping("insertUsuario")
    public ModelAndView inserirUsuario(UsuarioModel usuario) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        mv.setViewName("redirect:/");      
        return mv;
    }
    @PostMapping("insertUsuarioAdm")
    public ModelAndView inserirUsuarioAdm(AdmModel adm) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceAdm.salvarAdm(adm);
        mv.setViewName("redirect:/");      
        return mv;
    }
    @PostMapping("/login")
    public ModelAndView login(@Valid AdmModel adm, BindingResult br, HttpSession session, HttpServletResponse response) throws NoSuchAlgorithmException, ServiceExc{
       
        ModelAndView mv = new ModelAndView();
        mv.addObject("adm", new AdmModel());
        if(br.hasErrors()){
            mv.setViewName("Login/login");
        }
        AdmModel admLogin = serviceAdm.loginAdm(adm.getEmail(), Util.md5(adm.getSenha()));
        if(admLogin == null){
            mv.addObject("msg", "usuario n existe");
            mv.setViewName("Login/login");
        }else{
            session.setAttribute("usuarioLogado", admLogin);
            CookieSErvice.setCookie(response, "usuarioId", String.valueOf(admLogin.getId()),60*60 );
            return index();
        }
        return mv;
    }
    @PostMapping("/loginUser")
    public ModelAndView loginUser(@Valid UsuarioModel usuario, BindingResult br, HttpSession session, HttpServletResponse response) throws NoSuchAlgorithmException, ServiceExc{
       
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new UsuarioModel());
        if(br.hasErrors()){
            mv.setViewName("Login/loginUser");
        }
        UsuarioModel userLogin = serviceUsuario.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
        if(userLogin == null){
            mv.addObject("msg", "usuario n existe");
            mv.setViewName("Login/loginUser");
        }else{
            session.setAttribute("usuarioLogado", userLogin);
            CookieSErvice.setCookie(response, "usuarioId", String.valueOf(userLogin.getId()),60*60 );
            return indexUser();
        }
        return mv;
    }
    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        CookieSErvice.setCookie(response, "usuarioId", null, 0);
        return "redirect:/";

    }
}
