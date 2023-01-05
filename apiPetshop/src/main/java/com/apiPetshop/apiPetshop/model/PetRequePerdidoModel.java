package com.apiPetshop.apiPetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.apiPetshop.apiPetshop.enums.Raca;

@Entity
@Table(name = "petsrequeperdido")
public class PetRequePerdidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "nomedono")
    private String nomeDono;

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }
    @Column(name = "nome")
    @Size(min = 5, max = 35, message = "o nome deve conter minimo de 5 letras")
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "raca")
    @NotBlank(message = "deve se preencher o nome")
    @Enumerated(EnumType.STRING)
    private Raca raca;

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }


    @Column(name = "descrição")
    @Size(min = 5, max = 350, message = "descrição até 350 caracteres")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "imagem")
    @Lob
    private byte[] imagem;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    @Column(name = "endecoencdono")
    private String enderecoEncDono;

    public String getEnderecoEncDono() {
        return enderecoEncDono;
    }

    public void setEnderecoEncDono(String enderecoEncDono) {
        this.enderecoEncDono = enderecoEncDono;
    }
    @Column(name = "numteldono")
    private String numTelDono;

    public String getNumTelDono() {
        return numTelDono;
    }

    public void setNumTelDono(String numTelDono) {
        this.numTelDono = numTelDono;
    }


}
