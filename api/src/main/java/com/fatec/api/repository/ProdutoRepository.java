package com.fatec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Produto findById(long id);
}