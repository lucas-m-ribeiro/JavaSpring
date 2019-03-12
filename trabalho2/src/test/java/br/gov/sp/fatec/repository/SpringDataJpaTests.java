package br.gov.sp.fatec.repository;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.model.Pessoa;
import br.gov.sp.fatec.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SpringDataJpaTests {

	private static final String NOME = "test";
	private static final String ENDERECO = "Rua test";
	private static final String SEXO = "M";
	private static final String ESTADO = "Solteiro";
	private static final String CPF = "666.666.666-97";

	// private static final String SENHA = "Test";

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Test
	public void firstTesteInsercaoPessoaOk() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOME);
		pessoa.setEndereco(ENDERECO);
		pessoa.setEstado(ESTADO);
		pessoa.setCpf(CPF);
		pessoa.setSexo(SEXO);

		pessoaRepository.save(pessoa);
		assertTrue(pessoa.getId() != null);
	}

	@Test
	public void secondTesteRecuperacaoOK() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOME);
		pessoa.setEndereco(ENDERECO);
		pessoa.setEstado(ESTADO);
		pessoa.setCpf(CPF);
		pessoa.setSexo(SEXO);

		pessoaRepository.save(pessoa);

		pessoa = (Pessoa) pessoaRepository.findByNome(NOME);

		assertTrue(pessoa != null);
	}

	@Test
	public void thridTestInsercaoLivroOK() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOME);
		pessoa.setEndereco(ENDERECO);
		pessoa.setEstado(ESTADO);
		pessoa.setCpf(CPF);
		pessoa.setSexo(SEXO);

		pessoaRepository.save(pessoa);

		pessoa = (Pessoa) pessoaRepository.findByNome(NOME);

		Livro livro = new Livro();
		livro.setAutor("Teste 123");
		livro.setNome("Goose bumps teste");
		livro.setCategoria("Teste todo mundo");
		livro.setDescricao("Faz parte do tempo");
		livro.setPessoa(pessoa);

		livroRepository.save(livro);

		assertTrue(livro.getId() != null);
	}

	@Test
	public void forthTestPessoaLivro() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOME);
		pessoa.setEndereco(ENDERECO);
		pessoa.setEstado(ESTADO);
		pessoa.setCpf(CPF);
		pessoa.setSexo(SEXO);

		pessoaRepository.save(pessoa);

		pessoa = (Pessoa) pessoaRepository.findByNome(NOME);

		Livro livro = new Livro();
		livro.setAutor("Teste 123");
		livro.setNome("Goose bumps teste");
		livro.setCategoria("Teste todo mundo");
		livro.setDescricao("Faz parte do tempo");
		livro.setPessoa(pessoa);

		livroRepository.save(livro);

		livro = (Livro) livroRepository.findByNome("Goose bumps teste");

		assertTrue(livro.getPessoa() != null);
	}

}
