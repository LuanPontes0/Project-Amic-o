package com.apiPetshop.apiPetshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.dao.UsuarioDao;
import com.apiPetshop.apiPetshop.model.UsuarioModel;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioDao usuariorepositorio;

    @GetMapping("/inserirUsuario")
    public ModelAndView inserirtUsuario(UsuarioModel usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Usuario/formUsuario");
        mv.addObject("usuario", new UsuarioModel());
        return mv;
    }

    @PostMapping("insertUsuario")
    public ModelAndView inserirUsuario(UsuarioModel usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        usuariorepositorio.save(usuario);
        return mv;
    }
}
