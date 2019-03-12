package br.gov.sp.fatec.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.model.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SpringDataJpaTests {

	private static final String NOME = "test";
	private static final String ENDERECO = "Rua test";
	private static final String SEXO = "M";
	private static final String ESTADO = "Solteiro";
	private static final String CPF = "666.666.666-99";

	// private static final String SENHA = "Test";

	@Autowired
	private PessoaService pessoaService;

	@Test
	public void firstTesteInsercaoPessoaOk() {
		Pessoa pessoa = pessoaService.incluirPessoa(NOME, CPF, SEXO, ESTADO,
				ENDERECO);

		assertTrue(pessoa.getId() != null);
	}

	@Test
	public void secondTesteRecuperacaoOK() {

		Pessoa pessoa = pessoaService.incluirPessoa(NOME, CPF, SEXO, ESTADO,
				ENDERECO);

		pessoa = pessoaService.buscarPessoaByNome(NOME);

		assertTrue(pessoa != null);
	}

	@Test
	public void thridTestInsercaoLivroOK() {

		Pessoa pessoa = pessoaService.incluirPessoa(NOME, CPF, SEXO, ESTADO,
				ENDERECO);

		Livro livro = pessoaService.adicionarLivro(NOME, "Goose bumps teste",
				"Teste 123", "Teste todo mundo", "Faz parte do tempo");

		assertTrue(livro.getId() != null);
	}

	@Test
	public void forthTestPessoaLivro() {
		Pessoa pessoa = pessoaService.incluirPessoa(NOME, CPF, SEXO, ESTADO,
				ENDERECO);

		Livro livro = pessoaService.adicionarLivro(NOME, "Goose bumps teste",
				"Teste 123", "Teste todo mundo", "Faz parte do tempo");
		
		livro = pessoaService.buscarLivroByNome("Goose bumps teste");

		assertTrue(livro.getPessoa() != null);
	}

}
