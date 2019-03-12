package br.gov.sp.fatec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.service.PessoaService;

//import br.gov.sp.fatec.service.UsuarioService;

@SpringBootApplication 
public class SpringDataJpaApplication implements CommandLineRunner {

	@Autowired
	private PessoaService pessoaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	public void run(String... args) throws Exception {
/*		pessoaService.incluirPessoa("gustavo", "37225355810", "M",
		 "Solteiro",
		 "Jacarei");

		pessoaService.adicionarLivro("gustavo", "Goose bumps",
		"Desconhecido", "Livro de terror", "Terror");
		System.out.println("Adicionado com sucesso!");

		Livro livro = pessoaService.buscarLivroByNome("Goose bumps");
		System.out.println("Nome do livro procurado : " + livro.getNome()
				+ " do autor : " + livro.getAutor());*/

	}
}
