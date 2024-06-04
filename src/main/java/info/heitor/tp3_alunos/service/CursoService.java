package info.heitor.tp3_alunos.service;

import info.heitor.tp3_alunos.Repository.CursoRepository;
import info.heitor.tp3_alunos.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Cacheable(value = "curso", key = "#id")
    public Optional<Curso> getCursoByIdComCache(Long id) {
        return cursoRepository.findById(id);
    }

    public Optional<Curso> getCursoByIdSemCache(Long id) {
        return cursoRepository.findById(id);
    }

    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso updateCurso(Long id, Curso cursoDetails) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoDetails.getNome());
            curso.setDescricao(cursoDetails.getDescricao());
            return cursoRepository.save(curso);
        }).orElse(null);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
