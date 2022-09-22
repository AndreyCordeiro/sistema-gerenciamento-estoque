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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

            List<Venda> vendaList = vendaRepository.findVendaByUsuarioId(id);
            if (!vendaList.isEmpty()) {
                List<RelatorioDTO> listaRetorno = montarRetorno(vendaList);

                retornoRelatorioDTO.setValorTotalVenda(recuperarValorTotal(listaRetorno));
                retornoRelatorioDTO.setUsuarioDTO(UtilUsuario.converteUsuario(usuario));
                retornoRelatorioDTO.setRelatorioDTO(listaRetorno);
                retornoRelatorioDTO.setMensagem("O usuário " + id + " realizou o total de " + vendaList.size() + " venda(s)");
                return retornoRelatorioDTO;
            } else {
                throw new InfoException("Ocorreu um erro ao buscar a(s) venda(s) do usuário " + id, HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }

    @Override
    public RetornoRelatorioDTO vendasFiltroPorData(String dataInicio, String dataFim) throws InfoException, ParseException {
        Date dataInicial = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
        Date dataFinal = new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
        RetornoRelatorioDTO retornoRelatorioDTO = new RetornoRelatorioDTO();

        List<Venda> vendaList = vendaRepository.findByDataVendaBetween(dataInicial, dataFinal);
        if (!vendaList.isEmpty()) {
            List<RelatorioDTO> listaRetorno = montarRetorno(vendaList);

            retornoRelatorioDTO.setValorTotalVenda(recuperarValorTotal(listaRetorno));
            retornoRelatorioDTO.setRelatorioDTO(listaRetorno);
            retornoRelatorioDTO.setMensagem(vendaList.size() + " venda(s) retornada(s)");
            return retornoRelatorioDTO;
        } else {
            throw new InfoException("Ocorreu um erro ao buscar a(s) venda(s) por filtro", HttpStatus.BAD_REQUEST);
        }
    }

    protected double recuperarValorTotal(List<RelatorioDTO> lista) {
        double valorTotal = 0.0;

        for (RelatorioDTO relatorioDTO : lista) {
            valorTotal += relatorioDTO.getValorUnitario() * relatorioDTO.getQuantidade();
        }
        return valorTotal;
    }

    protected List<RelatorioDTO> montarRetorno(List<Venda> vendaList) {
        List<RelatorioDTO> relatorioDTOList = new ArrayList<>();

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
                        relatorioDTOList.add(relatorioDTO);
                    }
                }
            }
        }
        return relatorioDTOList;
    }
}
