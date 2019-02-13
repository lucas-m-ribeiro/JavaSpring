package br.gov.sp.fatec;

public class GerarPessoa {
	
	private Dados dados;
	
	
	public GerarPessoa() {
		
	}
	
	public String gerar(){
		return "[ <" + dados.getDados() + "> ]";
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}
}


