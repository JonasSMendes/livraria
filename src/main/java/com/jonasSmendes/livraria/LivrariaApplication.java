package com.jonasSmendes.livraria;

import com.jonasSmendes.livraria.principal.ExibeLivraria;
import com.jonasSmendes.livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrariaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

	@Autowired
	private LivroRepository repository;


	@Override
	public void run(String... args) throws Exception {
		ExibeLivraria exibeLivraria = new ExibeLivraria(repository);

		exibeLivraria.exibeMenu();
	}
}
