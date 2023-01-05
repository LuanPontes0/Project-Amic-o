package com.apiPetshop.apiPetshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiPetshop.apiPetshop.model.AdmModel;


@Repository
public interface AdmDao extends JpaRepository<AdmModel, Integer> {

    
    public AdmModel findByEmail(String email);

    
    public AdmModel findByEmailAndSenha(String email, String senha);
}
