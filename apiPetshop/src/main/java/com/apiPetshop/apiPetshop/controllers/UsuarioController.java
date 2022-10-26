package com.apiPetshop.apiPetshop.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.dao.UsuarioDao;
import com.apiPetshop.apiPetshop.model.PetModel;
import com.apiPetshop.apiPetshop.model.UsuarioModel;
import com.apiPetshop.apiPetshop.service.ServiceUsuario;
import com.apiPetshop.apiPetshop.util.Util;

import Execeptions.ServiceExc;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioDao usuariorepositorio;
    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("usuario", new UsuarioModel());
        return mv;
    }
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
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

    @PostMapping("insertUsuario")
    public ModelAndView inserirUsuario(UsuarioModel usuario) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        mv.setViewName("redirect:/");      
        return mv;
    }
    @PostMapping("/login")
    public ModelAndView login(UsuarioModel usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc{
       
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new UsuarioModel());
        if(br.hasErrors()){
            mv.setViewName("Login/login");
        }
        UsuarioModel userLogin = serviceUsuario.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
        if(userLogin == null){
            mv.addObject("msg", "usuario n existe");
            mv.setViewName("Login/login");
        }else{
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }
        return mv;

    }
}
