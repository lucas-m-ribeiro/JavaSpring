package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	public Pessoa findByNome(String nome);

	public Pessoa findTop1ByNomeContains(String nome);

	public List<Pessoa> findByIdGreaterThan(Long id);

	@Query("select u from Pessoa u where u.nome like %?1%")
	public List<Pessoa> buscaPessoa(String nome);
}
