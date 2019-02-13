package br.gov.sp.fatec;

public class Pessoa implements Dados {

	private String nome;
	private String sobrenome;
	private int idade;
	private String telefone;
	
	public Pessoa(){
	}
	
	@Override
	public String getDados() {
		return "Seus dados sao: \n"
				+ "Nome: " + nome + "\n"
				+ "Sobrenome: " +sobrenome+ "\n"
				+ "Idade: " + idade + "\n"
				+ "Telefone: " + telefone + "\n";
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSobrenome(String sobrenome) {
	 	this.sobrenome = sobrenome;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
