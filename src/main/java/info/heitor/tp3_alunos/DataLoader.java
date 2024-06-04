package info.heitor.tp3_alunos;

import info.heitor.tp3_alunos.Repository.AlunoRepository;
import info.heitor.tp3_alunos.Repository.CursoRepository;
import info.heitor.tp3_alunos.model.Aluno;
import info.heitor.tp3_alunos.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {

        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        alunoRepository.save(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria");
        alunoRepository.save(aluno2);

        Curso curso1 = new Curso();
        curso1.setNome("Java");
        curso1.setDescricao("Curso de Java com spring boot");
        curso1.setAluno(aluno1);
        cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setNome("C#");
        curso2.setDescricao("Curso de C# com entity");
        curso2.setAluno(aluno2);
        cursoRepository.save(curso2);
    }
}