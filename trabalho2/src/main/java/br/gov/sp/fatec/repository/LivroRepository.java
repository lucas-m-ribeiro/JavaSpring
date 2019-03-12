package br.gov.sp.fatec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {

	@Query("select u from Livro u where u.nome like %?1%")
	public Livro findByNome(String nome);

}
