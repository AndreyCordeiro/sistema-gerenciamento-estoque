package com.sge.service;

import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.model.Endereco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;

@Service
public class EnderecoService {
    public static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    public Endereco findByCep(Long cep) throws ResourceNotFoundException {
        Endereco endereco = findByCepBrasilApi(cep);

        if (endereco == null) {
            throw new ResolutionException("O endereço com CEP '" + cep + "' não foi encontrado na base do Brasil API");
        } else {
            logger.info("Busca do CEP '" + cep + "' realizada com sucesso na base do Brasil API");
            return endereco;
        }
    }

    private Endereco findByCepBrasilApi(Long cep) throws ResourceNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://brasilapi.com.br/api/cep/v2/" + cep;

        try {
            return restTemplate.getForObject(url, Endereco.class);
        } catch (Exception e) {
            logger.error("Ocorreu um problema ao realizar a busca do CEP informado " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}