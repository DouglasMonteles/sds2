package com.devsuperior.dsdeliver.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	private ProductService service;
	
	public ProductResource(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> listProductsDTO = service.findAll();
		return ResponseEntity.ok().body(listProductsDTO);
	}
	
}
