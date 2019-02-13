package br.gov.sp.fatec;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/* 
  Nomes: Gabriel Koyama Alves
  		 Lucas Monteiro Ribeiro
  		
 	Disciplina: Topicos especiais em informatica.
 */

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(	"ApplicationContext.xml");
		
		GerarPessoa gp = (GerarPessoa) context.getBean("pessoa");
		System.out.println(gp.gerar());
		
		 Pessoa p = (Pessoa) context.getBean("mensagem"); 
	     p.setNome("Maria");
	     p.setSobrenome("alvez");
	     p.setIdade(50);
	     p.setTelefone("121211212");
	     System.out.println(p.getDados());
	     context.close();
	}

}
