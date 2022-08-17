package com.sge.service.endereco;

import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.Endereco;

public interface EnderecoService {
    Endereco findByCep(Long cep) throws ResourceNotFoundException;

    Endereco findByCepBrasilApi(Long cep) throws ResourceNotFoundException;
}
