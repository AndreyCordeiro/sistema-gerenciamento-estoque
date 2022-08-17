package com.sge.service.cliente;

import com.sge.config.AppConfig;
import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Cliente;
import com.sge.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.module.ResolutionException;

@Service
public class ClienteServiceImpl implements ClienteService {
    public static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public Boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    public Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            throw new ResolutionException("O cliente " + id + " não foi encontrado");
        } else {
            return cliente;
        }
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> findAllByNome(String nome, Pageable pageable) {
        return clienteRepository.findByNome(nome, pageable);
    }

    public Cliente saveCliente(Cliente cliente) throws BadResourceException, ResourceAlreadyExistsException {
        if (cliente != null) {
            cliente.setSenha(AppConfig.passwordEncoder().encode(cliente.getSenha()));
            clienteRepository.save(cliente);

            logger.info("Cliente " + cliente.getId() + " salvo com sucesso!");
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o cliente");
            badResourceException.addErrorMessage("O cliente está vazio ou é nulo");
            throw badResourceException;
        }
        return cliente;
    }

    public void updateCliente(Cliente cliente) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(cliente.getNome())) {
            if (!existsById(cliente.getId())) {
                throw new ResourceNotFoundException("O cliente " + cliente.getId() + " não foi encontrado");
            }
            clienteRepository.save(cliente);
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o cliente");
            badResourceException.addErrorMessage("O Cliente está vazio ou é nulo");
            throw badResourceException;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("O cliente " + id + " não foi encontrado");
        } else {
            clienteRepository.deleteById(id);
        }
    }

    public Long count() {
        return clienteRepository.count();
    }
}
