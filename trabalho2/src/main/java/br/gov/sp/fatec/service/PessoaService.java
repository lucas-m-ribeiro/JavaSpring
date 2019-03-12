package br.gov.sp.fatec.service;

import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.model.Pessoa;

public interface PessoaService {

	public Pessoa incluirPessoa(String nome, String cpf, String sexo,
			String estado, String endereco);

	public void excluirPessoa(String nome);

	public Livro adicionarLivro(String pessoa_nome, String nome, String autor,
			String descricao, String categoria);

	public void excluirLivro(String nome);

	public Livro buscarLivroByNome(String nome);
	
	public Pessoa buscarPessoaByNome(String nome);

}
