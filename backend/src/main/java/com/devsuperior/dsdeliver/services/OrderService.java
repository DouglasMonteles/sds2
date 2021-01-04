package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	private OrderRepository repository;
	private ProductRepository productRepository;
	
	public OrderService(OrderRepository repository, ProductRepository productRepository) {
		this.repository = repository;
		this.productRepository = productRepository;
	}
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> listOrders = repository.findOrderWithProducts();
		return listOrders
				.stream()
				.map(order -> new OrderDTO(order))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO orderDTO) {
		Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude(), 
				orderDTO.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO prod : orderDTO.getProducts()) {
			Product product = productRepository.getOne(prod.getId()); // Não vai no db, ele cria um entidade gerenciada pelo jpa, sendo salva tbm as associacoes ao salvar a order
			order.getProducts().add(product);
		}
		
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}
