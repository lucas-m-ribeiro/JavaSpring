package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ped_id")
	private Long id;
	
	@Column(name = "ped_nome",length = 20, nullable = false)
	private String nome;
	
	@Column(name = "ped_endereco",length = 20, nullable = false)
	private String endereco;
	
	@Column(name = "ped_codBarras", length = 20, nullable = false)
	private int codigo_de_barras;
	
	@Column(name = "ped_tipo", length = 20, nullable = false)
	private String tipo;
	
	@Column(name = "ped_pais", length = 20, nullable = false)
	private String pais;
	
	
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
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int getCodigoBarras() {
		return codigo_de_barras;
	}
	
	public void setCodigoBarras(int codigo_de_barras) {
		this.codigo_de_barras = codigo_de_barras;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
}
