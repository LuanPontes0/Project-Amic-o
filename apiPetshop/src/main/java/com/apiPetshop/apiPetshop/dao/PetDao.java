package com.apiPetshop.apiPetshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apiPetshop.apiPetshop.model.PetModel;

public interface PetDao extends JpaRepository<PetModel, Integer> {

    /*será usado o jpql para filtro de pesquisa 
    @Query("select j from pets j where j.status = 'ADOCAO' ")
    public List<PetModel> findByStatusAdocao();*/

}
