package com.sge.service.relatorio;

import com.sge.dto.RelatorioDTO;
import com.sge.dto.RetornoRelatorioDTO;
import com.sge.entity.Usuario;
import com.sge.entity.Venda;
import com.sge.exceptions.InfoException;
import com.sge.repository.VendaRepository;
import com.sge.service.usuario.UsuarioServiceImpl;
import com.sge.util.UtilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioServiceImpl implements RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public RetornoRelatorioDTO vendasPorUsuario(Long id) throws InfoException {
        Usuario usuario = usuarioService.encontrarUsuarioPorId(id);
        if (usuario != null) {
            RetornoRelatorioDTO retornoRelatorioDTO = new RetornoRelatorioDTO();
            List<RelatorioDTO> relatorioDTOList = new ArrayList<>();

            List<Venda> vendaList = vendaRepository.findVendaByUsuarioId(id);

            if (!vendaList.isEmpty()) {
                for (Venda venda : vendaList) {
                    RelatorioDTO relatorioDTO = RelatorioDTO
                            .builder()
                            .usuarioId(venda.getUsuario().getId())
                            .vendaId(venda.getId())
                            .build();
                    relatorioDTOList.add(relatorioDTO);
                }
                retornoRelatorioDTO.setUsuarioDTO(UtilUsuario.converteUsuario(usuario));
                retornoRelatorioDTO.setRelatorioDTO(relatorioDTOList);
                retornoRelatorioDTO.setMensagem("O usuário " + id + " realizou o total de " + vendaList.size() + " vendas");
                return retornoRelatorioDTO;
            } else {
                throw new InfoException("Ocorreu um erro ao buscar as vendas do usuário " + id, HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }
}
