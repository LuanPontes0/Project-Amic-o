package com.apiPetshop.apiPetshop.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiPetshop.apiPetshop.dao.AdmDao;
import com.apiPetshop.apiPetshop.dao.UsuarioDao;
import com.apiPetshop.apiPetshop.model.AdmModel;
import com.apiPetshop.apiPetshop.model.UsuarioModel;
import com.apiPetshop.apiPetshop.util.Util;

import Execeptions.CriptoExistException;
import Execeptions.EmailExistsException;
import Execeptions.ServiceExc;

@Service
public class ServiceAdm {
    @Autowired
    private AdmDao repositorioAdm;

    public void salvarAdm(AdmModel adm) throws Exception{
        try {
            if(repositorioAdm.findByEmail(adm.getEmail()) != null){
                throw new EmailExistsException("ja existe um email" + adm.getEmail());
            }
            adm.setSenha(Util.md5(adm.getSenha()));

        } catch (NoSuchAlgorithmException e) {
           throw new CriptoExistException("erro na senha");
        }
        repositorioAdm.save(adm);
    }
    public AdmModel loginAdm(String email, String senha) throws ServiceExc{
        AdmModel admLogin = repositorioAdm.findByEmailAndSenha(email, senha);
        return admLogin;

    }
}
