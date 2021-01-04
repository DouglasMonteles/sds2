package com.devsuperior.dsdeliver.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	private OrderService service;
	
	public OrderResource(OrderService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> listOrdersDTO = service.findAll();
		return ResponseEntity.ok().body(listOrdersDTO);
	}
	
}
