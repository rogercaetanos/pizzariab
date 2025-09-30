package com.itb.inf2bm.pizzariab.model.services;


import com.itb.inf2bm.pizzariab.model.entity.Produto;
import com.itb.inf2bm.pizzariab.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Autowired :  Injeção de Dependência, garante a depedência entre as classes

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Salvar Produto
    public Produto save(Produto produto) {
        produto.setCodStatus(true);
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    // Listar Produto por Id

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado com o id " + id));
    }

    // Atualizar Produto

    public Produto update(Long id, Produto produto) {
        Produto produtoExistente = findById(id);
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setValorVenda(produto.getValorVenda());
        produtoExistente.setValorCompra(produto.getValorCompra());
        produtoExistente.setTipo(produto.getTipo());
        produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoExistente.setCodStatus(produto.isCodStatus());
        return produtoRepository.save(produtoExistente);
    }

    // Excluir Produto ( Exclusão Física )

    public void delete(Long id) {
        Produto produtoExistente = findById(id);
        produtoRepository.delete(produtoExistente);
    }

}
