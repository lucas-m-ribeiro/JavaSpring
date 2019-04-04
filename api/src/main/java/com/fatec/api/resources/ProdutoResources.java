package com.fatec.api.resources;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fatec.api.model.Produto;
import com.fatec.api.model.View;
import com.fatec.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class ProdutoResources {
	
	@Autowired
	ProdutoRepository pr;
	
	//salva produto
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto, HttpServletRequest request, HttpServletRequest response) {
		if(produto == null) {			
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		produto = pr.save(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	//pesquisa produto por id
	
	@GetMapping("/produto/{id}")
	@JsonView(View.ProdutoSemId.class)
	public ResponseEntity<Produto> listarProdutoUnico(@PathVariable(value="id")long id) {
		Produto produto = pr.findById(id);
		if(produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	//lista todos os produtos
	
	@GetMapping("/produto")
	@JsonView(View.ProdutoCompleto.class)
	public ResponseEntity<Collection<Produto>> listarProdutos(){
		return new ResponseEntity<Collection<Produto>>(pr.findAll(), HttpStatus.OK);	
	}	
}