package com.apiPetshop.apiPetshop.enums;

public enum Status {
    PERDIDO("Perdido"),
    ADOCAO("Adoção");

    private String status;

    private Status(String status){
        this.status = status;
    }
}
