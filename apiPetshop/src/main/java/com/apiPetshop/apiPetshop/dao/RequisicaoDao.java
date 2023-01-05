package com.apiPetshop.apiPetshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiPetshop.apiPetshop.model.RequisicaoModel;
@Repository
public interface RequisicaoDao extends JpaRepository<RequisicaoModel, Integer>{

    public List<RequisicaoModel> findByNomeContainingIgnoreCase(String nome);
    
}
