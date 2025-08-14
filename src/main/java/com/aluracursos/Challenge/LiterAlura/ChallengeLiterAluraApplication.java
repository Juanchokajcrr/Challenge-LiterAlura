package com.aluracursos.Challenge.LiterAlura;

import com.aluracursos.Challenge.LiterAlura.principal.Main;
import com.aluracursos.Challenge.LiterAlura.repository.AutorRepository;
import com.aluracursos.Challenge.LiterAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(libroRepository, autorRepository);
        main.muestraElMenu();
    }
}
