package com.sge.service.venda;

import com.sge.entity.ItensVenda;
import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;
import com.sge.repository.VendaRepository;
import com.sge.service.itensVenda.ItensVendaServiceImpl;
import com.sge.util.UtilVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImpl extends ItensVendaServiceImpl implements VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public List<Venda> buscarTodos() {
        return vendaRepository.findAll();
    }

    @Override
    public Venda inserir(Venda venda) throws InfoException {
        if (UtilVenda.validarVenda(venda)) {
            for (ItensVenda itensVenda : venda.getItensVenda()) {
                itensVenda.setVenda(venda);
                inserir(itensVenda);
            }
            return vendaRepository.save(venda);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar venda", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void excluir(Long id) throws InfoException {
        Optional<Venda> venda = vendaRepository.findById(id);

        if (venda.isPresent()) {
            vendaRepository.delete(venda.get());
        } else {
            throw new InfoException("Venda não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
