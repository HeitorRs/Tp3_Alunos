package info.heitor.tp3_alunos.controller;

import info.heitor.tp3_alunos.model.Produto;
import info.heitor.tp3_alunos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.createProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarProdutoPorId(@PathVariable String id) {
        return produtoService.findProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable String id) {
        produtoService.deleteProdutoPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable String id, @RequestBody Produto produto) {
        return produtoService.updateProduto(id, produto);
    }
}
