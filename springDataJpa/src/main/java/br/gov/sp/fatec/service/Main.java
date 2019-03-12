package br.gov.sp.fatec.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.repository.PedidoRepository;
import br.gov.sp.fatec.repository.ClienteRepository;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PedidoRepository pedRepository = (PedidoRepository ) context.getBean("pedRepository");
		ClienteRepository clireposotiry = (ClienteRepository) context.getBean("clienteRepository");
		
		//terminar a main

	}

}
