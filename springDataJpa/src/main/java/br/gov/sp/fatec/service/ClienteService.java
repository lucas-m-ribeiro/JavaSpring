package br.gov.sp.fatec.service;

import br.gov.sp.fatec.model.Cliente;

public interface ClienteService {
	
	public Cliente addCliente(String nome, int idade, int rg, int telefone, String endereco);
	
	//add mais um metodo
}
