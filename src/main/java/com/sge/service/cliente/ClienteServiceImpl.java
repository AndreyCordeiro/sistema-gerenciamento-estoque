package com.sge.service.cliente;

import com.sge.config.AppConfig;
import com.sge.exceptions.BadResourceException;
import com.sge.exceptions.ResourceAlreadyExistsException;
import com.sge.exceptions.ResourceNotFoundException;
import com.sge.model.entity.Pessoa;
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

    public Pessoa findById(Long id) {
        Pessoa pessoa = clienteRepository.findById(id).orElse(null);

        if (pessoa == null) {
            throw new ResolutionException("O cliente " + id + " não foi encontrado");
        } else {
            return pessoa;
        }
    }

    public Page<Pessoa> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<Pessoa> findAllByNome(String nome, Pageable pageable) {
        return clienteRepository.findByNome(nome, pageable);
    }

    public Pessoa saveCliente(Pessoa pessoa) throws BadResourceException, ResourceAlreadyExistsException {
        if (pessoa != null) {
            pessoa.setSenha(AppConfig.passwordEncoder().encode(pessoa.getSenha()));
            clienteRepository.save(pessoa);

            logger.info("Cliente " + pessoa.getId() + " salvo com sucesso!");
        } else {
            BadResourceException badResourceException = new BadResourceException("Erro ao salvar o cliente");
            badResourceException.addErrorMessage("O cliente está vazio ou é nulo");
            throw badResourceException;
        }
        return pessoa;
    }

    public void updateCliente(Pessoa pessoa) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(pessoa.getNome())) {
            if (!existsById(pessoa.getId())) {
                throw new ResourceNotFoundException("O cliente " + pessoa.getId() + " não foi encontrado");
            }
            clienteRepository.save(pessoa);
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
