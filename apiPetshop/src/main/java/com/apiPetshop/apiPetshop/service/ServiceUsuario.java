package com.apiPetshop.apiPetshop.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiPetshop.apiPetshop.dao.UsuarioDao;
import com.apiPetshop.apiPetshop.model.UsuarioModel;
import com.apiPetshop.apiPetshop.util.Util;

import Execeptions.CriptoExistException;
import Execeptions.EmailExistsException;
import Execeptions.ServiceExc;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioDao repositorioUsuario;

    public void salvarUsuario(UsuarioModel user) throws Exception{
        try {
            if(repositorioUsuario.findByEmail(user.getEmail()) != null){
                throw new EmailExistsException("ja existe um email" + user.getEmail());
            }
            user.setSenha(Util.md5(user.getSenha()));

        } catch (NoSuchAlgorithmException e) {
           throw new CriptoExistException("erro na senha");
        }
        repositorioUsuario.save(user);
    }
    public UsuarioModel loginUser(String email, String senha) throws ServiceExc{
        UsuarioModel userLogin = repositorioUsuario.findByEmailAndSenha(email, senha);
        return userLogin;

    }
}
