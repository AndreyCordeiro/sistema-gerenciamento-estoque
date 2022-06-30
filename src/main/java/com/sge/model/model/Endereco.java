package com.sge.model.model;

import lombok.Data;

@Data
public class Endereco {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
    private Localizacao location;
}