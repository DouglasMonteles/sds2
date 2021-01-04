package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> listProducts = repository.findAllByOrderByNameAsc();
		return listProducts
				.stream()
				.map(product -> new ProductDTO(product))
				.collect(Collectors.toList());
	}
	
}
