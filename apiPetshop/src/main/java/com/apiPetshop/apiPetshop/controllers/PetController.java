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
import com.apiPetshop.apiPetshop.dao.PetPerdidoDao;
import com.apiPetshop.apiPetshop.dao.PetRequePerdidoDao;
import com.apiPetshop.apiPetshop.dao.RequisicaoDao;
import com.apiPetshop.apiPetshop.model.PetModel;
import com.apiPetshop.apiPetshop.model.PetPerdidoModel;
import com.apiPetshop.apiPetshop.model.PetRequePerdidoModel;
import com.apiPetshop.apiPetshop.model.RequisicaoModel;
import com.apiPetshop.apiPetshop.util.UploadUtil;

@Controller
public class PetController {
    String pastaUploadImagem = "C:\\Users\\Mauricio Stocker\\Desktop\\Project-Amic-o-testeMAur1\\apiPetshop\\src\\main\\resources\\static\\img\\img-uploads";
    @Autowired
    private PetDao petrepositorio;
    @Autowired
    private PetPerdidoDao petPerdrepositorio;
    @Autowired
    private PetRequePerdidoDao petReqPerdrepositorio;

    @Autowired
    private RequisicaoDao reqrepositorio;

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

    @PostMapping("insertPetPerdido")
    public ModelAndView inserirPetPerdido(@ModelAttribute PetPerdidoModel petPerdido,
            @RequestParam("file") MultipartFile file) {
        try {
            petPerdido.setImagemPerdido(file.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("Pet/formPetPerdido");

        mv.addObject("petsPerdido", petPerdido);
        petPerdrepositorio.save(petPerdido);
        mv.setViewName("redirect:/index");
        return mv;
    }

    @PostMapping("insertPetReqPerdido")
    public ModelAndView inserirPetReqPerdido(@ModelAttribute PetRequePerdidoModel petReqPerdido,
            @RequestParam("file") MultipartFile file) {
        try {
            petReqPerdido.setImagem(file.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("Pet/formPetReqUser");

        mv.addObject("petsReqPerdido", petReqPerdido);
        petReqPerdrepositorio.save(petReqPerdido);
        mv.setViewName("redirect:/indexUser");
        return mv;
    }

    @GetMapping("/img-uploads/{id}")
    @ResponseBody
    public byte[] exibeImagem(@PathVariable("id") Integer id) {
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
    /*
     * @GetMapping("/mostrarImagem/{imagem}")
     * public byte[] mostrarImagem(@PathVariable("imagem") String imagem) {
     * 
     * File imagemAquivo = new File(pastaUploadImagem + imagem);
     * if(){
     * 
     * }
     * try {
     * return Files.readAllBytes(imagemAquivo.toPath());
     * 
     * } catch (IOException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * }
     */

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute PetModel pet, @RequestParam("file") MultipartFile file) {
        try {
            pet.setImagem(file.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("Pet/alterar");
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

    @PostMapping("pesquisar-Req")
    public ModelAndView pesquisarPet(@RequestParam(required = false) String nome) {
        ModelAndView mv = new ModelAndView();
        List<RequisicaoModel> listaReq;
        if (nome == null || nome.trim().isEmpty()) {
            listaReq = reqrepositorio.findAll();
        } else {
            listaReq = reqrepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("petsLIstReq", listaReq);
        mv.setViewName("Pet/pesquisa-resultadoUserReq");
        return mv;
    }

    @GetMapping("/inserirPetUser")
    public ModelAndView inserirtPetUser(RequisicaoModel req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/formPetUser");
        mv.addObject("req", new RequisicaoModel());
        return mv;
    }

    @PostMapping("insertPetUser")
    public ModelAndView inserirPetUser(RequisicaoModel req) {
        ModelAndView mv = new ModelAndView("Pet/formPetUser");

        mv.addObject("req", req);
        reqrepositorio.save(req);
        mv.setViewName("redirect:/indexUser");
        return mv;
    }

    @GetMapping("Pet-AdicionadosUser")
    public ModelAndView listagemPetUser() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPetUser");
        mv.addObject("petsLIst", petrepositorio.findAll());
        return mv;
    }

    @GetMapping("Pet-AdicionadosPerdido")
    public ModelAndView listagemPetPerdido() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPetPerdidoUser");
        mv.addObject("petsLIstPerdido", petPerdrepositorio.findAll());
        return mv;
    }
    @GetMapping("Pet-AdicionadosReqPerdido")
    public ModelAndView listagemPetReqPerdido() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPetReqPerdidoUser");
        mv.addObject("petsLIstReqPerdido", petReqPerdrepositorio.findAll());
        return mv;
    }

    @GetMapping("Pet-AdicionadosReq")
    public ModelAndView listagemPetReq() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/ListPetRequisicao");
        mv.addObject("petsLIstReq", reqrepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterarReq/{id}")
    public ModelAndView alterarReq(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/alterarReq");
        RequisicaoModel req = reqrepositorio.getOne(id);
        mv.addObject("req", req);
        return mv;
    }

    @PostMapping("/alterarReq")
    public ModelAndView alterarReq(RequisicaoModel req) {
        ModelAndView mv = new ModelAndView();
        reqrepositorio.save(req);
        mv.setViewName("redirect:/Pet-AdicionadosReq");
        return mv;
    }
    @GetMapping("/excluirReq/{id}")
    public String excluirpetReq(@PathVariable("id") Integer id) {
        reqrepositorio.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/inserirPetPerdido")
    public ModelAndView inserirtPetPerdido(PetPerdidoModel petPerdido) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/formPetPerdido");
        mv.addObject("petsPerdido", new PetPerdidoModel());
        return mv;
    }



    @GetMapping("/inserirPetReqPerdido")
    public ModelAndView inserirtPetReqPerdido(PetRequePerdidoModel petReqPerdido) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pet/formPetReqUser");
        mv.addObject("petsReqPerdido", new PetRequePerdidoModel());
        return mv;
    }

    @GetMapping("/img-uploadsPerdido/{id}")
    @ResponseBody
    public byte[] exibeImagemPerdido(@PathVariable("id") Integer id) {
        PetPerdidoModel petPerdido = petPerdrepositorio.getOne(id);

        return petPerdido.getImagemPerdido();
    }
    @GetMapping("/img-uploadsReqPerdido/{id}")
    @ResponseBody
    public byte[] exibeImagemReqPerdido(@PathVariable("id") Integer id) {
        PetRequePerdidoModel petReqPerdido = petReqPerdrepositorio.getOne(id);

        return petReqPerdido.getImagem();
    }
    /*
     * @GetMapping("Pet-adocao")
     * public ModelAndView listaPetAdocao() {
     * ModelAndView mv = new ModelAndView();
     * mv.setViewName("Pet/pets-Adocao");
     * mv.addObject("petsAdocao", petrepositorio.findByStatusAdocao());
     * return mv;
     * }
     */

}
