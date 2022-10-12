package com.apiPetshop.apiPetshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiPetshop.apiPetshop.model.UsuarioModel;

public interface UsuarioDao extends JpaRepository<UsuarioModel, Integer>{
    
}
