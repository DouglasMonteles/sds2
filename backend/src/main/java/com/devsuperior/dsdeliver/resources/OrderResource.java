package com.devsuperior.dsdeliver.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO) {
		orderDTO = service.insert(orderDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(orderDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(orderDTO);
	}
	
	@PutMapping(value = "/{id}/delivered")
	public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id) {
		OrderDTO orderDTO = service.setDelivered(id);
		return ResponseEntity.ok().body(orderDTO);
	}
	
}
