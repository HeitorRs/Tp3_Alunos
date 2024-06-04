package info.heitor.tp3_alunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Tp3AlunosApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp3AlunosApplication.class, args);
    }

}
