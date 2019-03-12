package br.gov.sp.fatec;

import java.util.ArrayList;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.Autorizacao;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.AutorizacaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.service.UsuarioService;

public class App {
 
	public static void main( String[] args ){
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
 
		// Recupera os repositorios
		AutorizacaoRepository autorizacaoRepo = (AutorizacaoRepository) context.getBean("autorizacaoRepository");
		UsuarioRepository usuarioRepo = (UsuarioRepository) context.getBean("usuarioRepository");
		
		
		// Cria autorizacoes
		Autorizacao autorizacao1 = new Autorizacao();
		autorizacao1.setNome("Usuario");
		
		autorizacaoRepo.save(autorizacao1);
		
		Autorizacao autorizacao2 = new Autorizacao();
		autorizacao2.setNome("Administrador");
		
		autorizacaoRepo.save(autorizacao2);
		
		
		
		
		
		
		// Cria um usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Paulo");
		usuario.setSenha("senha");
		usuario.setAutorizacoes(new ArrayList<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao1);
		usuario.getAutorizacoes().add(autorizacao2);
		
		usuarioRepo.save(usuario);
		
		System.out.println("Id: " + usuario.getId());
		
		
		
		// Cria usuario usando servico (FORMA CORRETA DE FAZER!)
		UsuarioService usuarioService = (UsuarioService)context.getBean("usuarioService");
		
		usuario = usuarioService.incluirUsuario("Ana", "123456", "Administrador");
		
		System.out.println("Id: " + usuario.getId());
		
		
		
		// Realiza varias consultas
		System.out.println("Resultado da busca 1: " + usuarioRepo.findByNome("Paulo").getNome());
		
		System.out.println("Resultado da busca 2: " + usuarioRepo.findTop1ByNomeContains("au").getNome());

		for(Usuario us: usuarioRepo.findByIdGreaterThan(0l)) {
			System.out.println("Usuario encontrado: " + us.getNome());
		}
		
		
		for(Usuario us: usuarioRepo.findByNomeContainsIgnoreCaseOrAutorizacoesNomeContainsIgnoreCase("n", "m")) {
			System.out.println("Usuario encontrado 2 (or): " + us.getNome());
		}
		
		for(Usuario us: usuarioRepo.findByAutorizacoesNomeContainsIgnoreCase("Usuario")) {
			System.out.println("Usuario encontrado 3: " + us.getNome());
		}
		
		for(Usuario us: usuarioRepo.buscaUsuario("a")) {
			System.out.println("Usuario encontrado 4: " + us.getNome());
		}
		
		// Gerando erro de proposito ao tentar criar dois usuarios com mesmo nome
		try {
			usuarioService.incluirDoisUsuarios("Luis", "Luis");
		}
		catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			e.printStackTrace();
		}
		
		// Mesmo o erro ocorrendo somente no segundo insert, nada se grava por conta da transacao
		usuario = usuarioRepo.findByNome("Luis");
		if(usuario == null) {
			System.out.println("Usuario Luis nao foi encontrado!");
		}
		
		// Exclui usuario
		usuarioRepo.delete(usuarioRepo.findByNome("Paulo"));
		usuarioRepo.delete(usuarioRepo.findByNome("Ana"));
		// Exclui autorizacoes
		autorizacaoRepo.delete(autorizacao1);
		autorizacaoRepo.delete(autorizacao2);
		
		context.close();
    }
}
