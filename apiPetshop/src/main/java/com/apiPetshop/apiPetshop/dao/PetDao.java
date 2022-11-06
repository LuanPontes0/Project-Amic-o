package com.apiPetshop.apiPetshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiPetshop.apiPetshop.enums.Status;
import com.apiPetshop.apiPetshop.model.PetModel;

@Repository
public interface PetDao extends JpaRepository<PetModel, Integer> {

    /* será usado o jpql para filtro de pesquisa */
   /*  @Query("SELECT u FROM User u WHERE u.status = 'ADOCAO' ")
    public List<PetModel> findByStatusAdocao(Status status);*/
    public List<PetModel> findByNomeContainingIgnoreCase(String nome);
    

}
