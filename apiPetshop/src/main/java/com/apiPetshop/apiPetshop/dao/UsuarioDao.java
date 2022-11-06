package com.apiPetshop.apiPetshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiPetshop.apiPetshop.model.UsuarioModel;
@Repository
public interface UsuarioDao extends JpaRepository<UsuarioModel, Integer> {

    
    public UsuarioModel findByEmail(String email);

    
    public UsuarioModel findByEmailAndSenha(String email, String senha);
}
