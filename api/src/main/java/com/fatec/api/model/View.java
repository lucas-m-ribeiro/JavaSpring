package com.fatec.api.model;

public class View {
	
	/*
	 * Estas classes servem como um label para os atributos que desejamos serializar 
	 * para fazer a serialização basta utilizar a anotação @JsonView e passar o nome da classe label.
	 * 
	 * */
	
	public static class ProdutoCompleto extends ProdutoSemId{
	}
	
	public static class ProdutoSemId{
		
	}
}
