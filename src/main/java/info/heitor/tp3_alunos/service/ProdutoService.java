package info.heitor.tp3_alunos.service;

import info.heitor.tp3_alunos.Repository.ProdutoRepository;
import info.heitor.tp3_alunos.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findProdutoPorId(String id) {
        return produtoRepository.findById(id);
    }

    public void deleteProdutoPorId(String id) {
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(String id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            return produtoRepository.save(produto);
        }).orElse(null);
    }
}
