package info.heitor.tp3_alunos.service;

import info.heitor.tp3_alunos.Repository.AlunoRepository;
import info.heitor.tp3_alunos.Repository.CursoRepository;
import info.heitor.tp3_alunos.model.Aluno;
import info.heitor.tp3_alunos.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno updateAluno(Long id, Aluno alunoDetails) {
        return alunoRepository.findById(id).map(aluno -> {
            alunoDetails.setNome(alunoDetails.getNome());
            return alunoRepository.save(alunoDetails);
        }).orElse(null);
    }

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    public Curso addAlunoToCurso(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno not found"));
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso not found"));

        curso.setAluno(aluno);
        return cursoRepository.save(curso);
    }
}
