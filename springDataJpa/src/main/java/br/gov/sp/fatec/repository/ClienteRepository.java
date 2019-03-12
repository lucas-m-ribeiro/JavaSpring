package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	//@Query();
	public Cliente findByNome(String nome);
	
	//@Query();
	public List<Cliente> findByIdGreaterThan(Long id);
}