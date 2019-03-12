package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //classe de persistencia (classe que sera salvo no banco)
@Table(name = "aut_autorizacao")// @table tabela do banco
public class Autorizacao {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)//tipo de estrategia adotada para gerar id
    @Column(name = "aut_id")//@colum nome da coluna do banco
	private Long id;
    
    @Column(name = "aut_nome", unique=true, length = 20, nullable = false) //@colum nome da coluna do banco
    private String nome;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
