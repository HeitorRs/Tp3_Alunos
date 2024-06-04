package info.heitor.tp3_alunos.controller;

import info.heitor.tp3_alunos.model.Curso;
import info.heitor.tp3_alunos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService CursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = CursoService.getAllCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/comcache/{id}")
    public String getCursoByIdComCache(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        Optional<Curso> curso = CursoService.getCursoByIdComCache(id);
        long end = System.currentTimeMillis();
        long diff = end - start;
        return "Duração com cache " + diff + "ms";
    }

    @GetMapping("/semcache/{id}")
    public String getCursoByIdSemCache(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        Optional<Curso> curso = CursoService.getCursoByIdSemCache(id);
        long end = System.currentTimeMillis();
        long diff = end - start;
        return "Duração sem cache " + diff + "ms";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoByIdCache(@PathVariable Long id) {
        Optional<Curso> curso = CursoService.getCursoById(id);
        return curso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso novoCurso = CursoService.createCurso(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        try {
            Curso updatedCurso = CursoService.updateCurso(id, cursoDetails);
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        if (CursoService.getCursoByIdComCache(id).isPresent()) {
            CursoService.deleteCurso(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
