package com.itb.inf2bm.pizzariab.controller;

import com.itb.inf2bm.pizzariab.model.entity.Produto;
import com.itb.inf2bm.pizzariab.model.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 @RequestBody   : Corpo da Requisição ( Recebendo um objeto JSON )
 ResponseEntity : Toda resposta HTTP (status, cabeçalhos e corpo, aqui temos mais controle sobre o que é devolvido para o cliente
 1. Status HTTP: (200 ok, 201 CREATED, 404 NOT FOUND etc ...)
 2. Headers:  ( Cabeçalhos extras, como Location, Authorization etc ...)
 3. Body:   ( O objeto que será convertido em JSON/XML para o cliente )

*/

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
      return ResponseEntity.ok(produtoService.findAll());
    }

    // Salvar Produto

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto novo = produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // Buscar Produto por ID

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProdutoPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(produtoService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "O id informado não é válido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Produto não encontrado com o id: " + id
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable String id, @RequestBody Produto produto) {
        try {
            return ResponseEntity.ok(produtoService.update(Long.parseLong(id), produto));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "O id informado não é válido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Produto não encontrado com o id: " + id
                    )
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirProdutoPorId(@PathVariable String id) {
        try {
            produtoService.delete(Long.parseLong(id));
            return ResponseEntity.ok().body(
                    Map.of(
                            "status", 200,
                            "message", "Produto excluído com sucesso: " + id
                    )
            );
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "O id informado não é válido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Produto não encontrado com o id: " + id
                    )
            );
        }
    }

}
