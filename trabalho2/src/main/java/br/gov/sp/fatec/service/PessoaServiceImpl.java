package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.model.Pessoa;
import br.gov.sp.fatec.repository.LivroRepository;
import br.gov.sp.fatec.repository.PessoaRepository;

@Service("pessoaService")
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Autowired
	private LivroRepository livroRepo;

	public void setPessoaRepo(PessoaRepository usuarioRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	public void setLivroRepo(LivroRepository livroRepo) {
		this.livroRepo = livroRepo;
	}

	@Transactional
	public Pessoa incluirPessoa(String nome, String cpf, String sexo,
			String estado, String endereco) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setCpf(cpf);
		pessoa.setSexo(sexo);
		pessoa.setEstado(estado);
		pessoa.setEndereco(endereco);

		pessoaRepo.save(pessoa);
		
		return pessoa;
	}

	@Transactional
	public void excluirPessoa(String nome) {

	}

	public Livro adicionarLivro(String pessoa_nome, String nome, String autor,
			String descricao, String categoria) {

		Pessoa pessoa = pessoaRepo.findByNome(pessoa_nome);

		Livro livro = new Livro();
		livro.setAutor(autor);
		livro.setNome(nome);
		livro.setCategoria(categoria);
		livro.setDescricao(descricao);
		livro.setPessoa(pessoa);

		livroRepo.save(livro);
		
		return livro;
	}

	public void excluirLivro(String nome) {

	}

	public Livro buscarLivroByNome(String nome) {
		Livro livro = livroRepo.findByNome(nome);
		return livro;
	}

	public Pessoa buscarPessoaByNome(String nome) {
		Pessoa pessoa= pessoaRepo.findByNome(nome);
		return pessoa;
	}
}
