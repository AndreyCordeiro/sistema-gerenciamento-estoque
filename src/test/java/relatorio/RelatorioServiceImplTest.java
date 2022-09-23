package relatorio;

import com.sge.dto.RelatorioDTO;
import com.sge.service.relatorio.RelatorioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RelatorioServiceImplTest extends RelatorioServiceImpl {

    @Test
    public void recuperarValorTotal() {
        List<RelatorioDTO> lista = new ArrayList<>();
        RelatorioDTO relatorioDTO1 = new RelatorioDTO();
        RelatorioDTO relatorioDTO2 = new RelatorioDTO();
        RelatorioDTO relatorioDTO3 = new RelatorioDTO();

        relatorioDTO1.setValorUnitario(102.0);
        relatorioDTO1.setQuantidade(2.0);
        lista.add(relatorioDTO1);
        relatorioDTO2.setValorUnitario(99.99);
        relatorioDTO2.setQuantidade(26.0);
        lista.add(relatorioDTO2);
        relatorioDTO3.setValorUnitario(250.0);
        relatorioDTO3.setQuantidade(8.0);
        lista.add(relatorioDTO3);

        Assertions.assertEquals(4803.74, recuperarValorTotal(lista));
    }
}
