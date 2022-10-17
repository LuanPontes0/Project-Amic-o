package com.apiPetshop.apiPetshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.dao.PetDao;
import com.apiPetshop.apiPetshop.model.PetModel;

@Controller
public class PetController {
    @Autowired
    private PetDao petrepositorio;

    @GetMapping("/inserirPet")
    public ModelAndView inserirtPet(PetModel pet) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/formPet");
        mv.addObject("pet", new PetModel());
        return mv;
    }

    @PostMapping("insertPet")
    public ModelAndView inserirPet(PetModel pet) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/Pet-Adicionados");
        petrepositorio.save(pet);  
        return mv;
    }

    @GetMapping("Pet-Adicionados")
    public ModelAndView listagemPet() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPet");
        mv.addObject("petsLIst", petrepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/alterar");
        PetModel pet = petrepositorio.getOne(id);
        mv.addObject("pet", pet);
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(PetModel pet) {
        ModelAndView mv = new ModelAndView();
        petrepositorio.save(pet);
        mv.setViewName("redirect:/Pet-Adicionados");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirpet(@PathVariable("id") Integer id) {
        petrepositorio.deleteById(id);
        return "redirect:/Pet-Adicionados";
    }
  /*  @GetMapping("filtrar-pet")
    public ModelAndView filtroPet(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/filtroPets");
        return mv;
    }
   /*  @GetMapping("pet-perdidos")
    public ModelAndView listagemPetPerdido() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/pet-Perdidos");
        mv.addObject("petPerdidos", petrepositorio.findByStatusAdocao());
        return mv;
    }
*/
}
