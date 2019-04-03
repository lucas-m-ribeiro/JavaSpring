package com.fatec.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.api.model.Produto;
import com.fatec.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class ProdutoResources {
	
	@Autowired
	ProdutoRepository pr;
	
	@GetMapping("/produto")
	public List<Produto> listarProdutos(){
		return pr.findAll();
	}
	
	@GetMapping("/produto/{id}")
	public Produto listarProdutoUnico(@PathVariable(value="id")long id) {
		return pr.findById(id);
	}
	
	@PostMapping("/produto")
	public Produto salvaProduto(@RequestBody Produto produto){
		return pr.save(produto);
	}

}
