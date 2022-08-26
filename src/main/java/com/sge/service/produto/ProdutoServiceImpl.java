package com.sge.service.produto;

import com.sge.exceptions.InfoException;
import com.sge.entity.Produto;
import com.sge.repository.ProdutoRepository;
import com.sge.util.UtilProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto inserir(Produto produto) throws InfoException {
        if (UtilProduto.validarProduto(produto)) {
            return produtoRepository.save(produto);
        } else {
            throw new InfoException("Ocorreu um erro ao cadastrar produto", HttpStatus.BAD_REQUEST);
        }
    }

    public Produto alterar(Long id, Produto produto) throws InfoException {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            Produto produtoBuilder = Produto.builder()
                    .id(id)
                    .descricaoCurta(produto.getDescricaoCurta() != null ? produto.getDescricaoCurta() : null)
                    .descricaoDetalhada(produto.getDescricaoDetalhada() != null ? produto.getDescricaoDetalhada() : null)
                    .valorCusto(produto.getValorCusto() != null ? produto.getValorCusto() : null)
                    .valorVenda(produto.getValorVenda() != null ? produto.getValorVenda() : null)
                    .marca(produto.getMarca() != null ? produto.getMarca() : null)
                    .categoria(produto.getCategoria() != null ? produto.getCategoria() : null)
                    .build();

            if (UtilProduto.validarProduto(produtoBuilder)) {
                produtoRepository.save(produtoBuilder);
            }
            return produtoBuilder;
        } else {
            throw new InfoException("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public void excluir(Long id) throws InfoException {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
        } else {
            throw new InfoException("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
