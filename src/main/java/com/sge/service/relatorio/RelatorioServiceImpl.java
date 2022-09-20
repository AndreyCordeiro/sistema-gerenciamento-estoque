package com.sge.service.relatorio;

import com.sge.dto.RelatorioDTO;
import com.sge.dto.RetornoRelatorioDTO;
import com.sge.entity.ItensVenda;
import com.sge.entity.Produto;
import com.sge.entity.Usuario;
import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;
import com.sge.repository.ItensVendaRepository;
import com.sge.repository.ProdutoRepository;
import com.sge.repository.VendaRepository;
import com.sge.service.usuario.UsuarioServiceImpl;
import com.sge.util.UtilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioServiceImpl implements RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensVendaRepository itensVendaRepository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public RetornoRelatorioDTO vendasPorUsuario(Long id) throws InfoException {
        Usuario usuario = usuarioService.encontrarUsuarioPorId(id);
        if (usuario != null) {
            RetornoRelatorioDTO retornoRelatorioDTO = new RetornoRelatorioDTO();
            List<RelatorioDTO> relatorioDTOList = new ArrayList<>();
            Double valorTotal = 0.0;

            List<Venda> vendaList = vendaRepository.findVendaByUsuarioId(id);
            if (!vendaList.isEmpty()) {
                for (Venda venda : vendaList) {
                    List<ItensVenda> itensVenda = itensVendaRepository.findItensVendasByVendaId(venda.getId());

                    if (itensVenda != null && itensVenda.size() > 0) {
                        for (ItensVenda item : itensVenda) {
                            Optional<Produto> produto = produtoRepository.findById(item.getProduto().getId());

                            if (produto.isPresent()) {
                                RelatorioDTO relatorioDTO = RelatorioDTO
                                        .builder()
                                        .nomeProduto(produto.get().getNome())
                                        .produtoId(produto.get().getId())
                                        .quantidade(item.getQuantidade())
                                        .valorUnitario(item.getValorUnitario())
                                        .vendaId(venda.getId())
                                        .build();
                                valorTotal += item.getValorUnitario() * item.getQuantidade();
                                relatorioDTOList.add(relatorioDTO);
                            }
                        }
                    }
                }
                retornoRelatorioDTO.setValorTotalVenda(valorTotal);
                retornoRelatorioDTO.setUsuarioDTO(UtilUsuario.converteUsuario(usuario));
                retornoRelatorioDTO.setRelatorioDTO(relatorioDTOList);
                retornoRelatorioDTO.setMensagem("O usuário " + id + " realizou o total de " + vendaList.size() + " venda(s)");
                return retornoRelatorioDTO;
            } else {
                throw new InfoException("Ocorreu um erro ao buscar a(s) venda(s) do usuário " + id, HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }
}
