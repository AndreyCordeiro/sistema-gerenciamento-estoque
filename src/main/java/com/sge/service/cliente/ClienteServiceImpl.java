package com.sge.service.cliente;

import com.sge.dto.ClienteDTO;
import com.sge.entity.Cliente;
import com.sge.exceptions.InfoException;
import com.sge.repository.ClienteRepository;
import com.sge.util.UtilCliente;
import com.sge.util.UtilCriptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> buscarTodos() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        if (listaClientes.size() > 0) {
            for (Cliente cliente : listaClientes) {
                clienteDTOList.add(UtilCliente.converteCliente(cliente));
            }
        }
        return clienteDTOList;
    }

    @Override
    public ClienteDTO inserir(Cliente cliente) throws InfoException {
        if (UtilCliente.validarCliente(cliente)) {
            cliente.setSenha(UtilCriptografia.passwordEncoder().encode(cliente.getSenha()));
            clienteRepository.save(cliente);
            return UtilCliente.converteCliente(cliente);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar cliente", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ClienteDTO alterar(Long id, Cliente cliente) throws InfoException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente clienteBuilder = Cliente.builder()
                    .id(id)
                    .nome(cliente.getNome() != null ? cliente.getNome() : null)
                    .documento(cliente.getDocumento() != null ? cliente.getDocumento() : null)
                    .endereco(cliente.getEndereco() != null ? cliente.getEndereco() : null)
                    .cep(cliente.getCep() != null ? cliente.getCep() : null)
                    .email(cliente.getEmail() != null ? cliente.getEmail() : null)
                    .senha(cliente.getSenha() != null ? UtilCriptografia.passwordEncoder().encode(cliente.getSenha()) : null)
                    .build();

            if (UtilCliente.validarCliente(clienteBuilder)) {
                clienteRepository.save(clienteBuilder);
            }
            return UtilCliente.converteCliente(cliente);
        } else {
            throw new InfoException("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
        } else {
            throw new InfoException("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
