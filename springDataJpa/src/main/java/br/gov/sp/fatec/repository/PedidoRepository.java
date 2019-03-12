package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Pedido;


public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	
	public Pedido findByNome(String nome);
	public Pedido findByTipo(String tipo);
	public List<Pedido> findByClienteNome(String nome);
}
