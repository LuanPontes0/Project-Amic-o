package com.apiPetshop.apiPetshop.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.apiPetshop.apiPetshop.dao.PetDao;
import com.apiPetshop.apiPetshop.model.PetModel;
import com.apiPetshop.apiPetshop.util.UploadUtil;

@Controller
public class PetController {
    String pastaUploadImagem = "C:\\Users\\Mauricio Stocker\\Desktop\\Project-Amic-o-testeMAur1\\apiPetshop\\src\\main\\resources\\static\\img\\img-uploads";
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
    public ModelAndView inserirPet(@ModelAttribute PetModel pet, @RequestParam("file") MultipartFile file) {
        try {
            pet.setImagem(file.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("Pet/formPet");

        mv.addObject("pet", pet);
        petrepositorio.save(pet);
        mv.setViewName("redirect:/Pet-Adicionados");
        return mv;
        }

    @GetMapping("/img-uploads/{id}")  
    @ResponseBody
    public byte[] exibeImagem(@PathVariable("id") Integer id){
        PetModel pet = petrepositorio.getOne(id);
        return pet.getImagem();
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
   /*  @GetMapping("/mostrarImagem/{imagem}")
    public byte[] mostrarImagem(@PathVariable("imagem") String imagem) {

        File imagemAquivo = new File(pastaUploadImagem + imagem);
        if(){

        }
        try {
            return Files.readAllBytes(imagemAquivo.toPath());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } */

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

    @GetMapping("filtro-pet")
    public ModelAndView filtroPet() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/filtroPet");
        return mv;
    }
    
    @PostMapping("pesquisar-pet")
    public ModelAndView pesquisarPet(@RequestParam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<PetModel> listaPet;
        if(nome == null || nome.trim().isEmpty() ){
            listaPet = petrepositorio.findAll();
        }else{
            listaPet = petrepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("ListaDePet", listaPet);
        mv.setViewName("Pet/pesquisa-resultadoUser");
        return mv;
    }
    @GetMapping("/inserirPetUser")
    public ModelAndView inserirtPetUser(PetModel pet) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/formPetUser");
        mv.addObject("pet", new PetModel());
        return mv;
    }

    @PostMapping("insertPetUser")
    public ModelAndView inserirPetUser(PetModel pet) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/Pet-Adicionados-User");
        petrepositorio.save(pet);
        return mv;
    }

    @GetMapping("Pet-AdicionadosUser")
    public ModelAndView listagemPetUser() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPetUser");
        mv.addObject("petsLIst", petrepositorio.findAll());
        return mv;
    }
  /*  @GetMapping("Pet-adocao")
    public ModelAndView listaPetAdocao() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/pets-Adocao");
        mv.addObject("petsAdocao", petrepositorio.findByStatusAdocao());
        return mv;
    }*/

}
