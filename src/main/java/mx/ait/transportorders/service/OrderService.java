package mx.ait.transportorders.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.CreateOrderRequest;
import mx.ait.transportorders.dto.StatusOrderRequest;
import mx.ait.transportorders.exception.OrdersException;
import mx.ait.transportorders.exception.StatusOrdersException;
import mx.ait.transportorders.model.Orders;
import mx.ait.transportorders.model.Status;
import mx.ait.transportorders.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private Log logging = LogFactory.getLog(OrderService.class);
	
	private final OrderRepository orderRepository;
	
	public Orders createOrder(CreateOrderRequest orderRequest) {
		
		Orders order = Orders.builder().status(Status.CREATED)
						.origin(orderRequest.getOrigin())
						.destination(orderRequest.getDestination())
						.createdAt(LocalDateTime.now()).build();
		
		order = orderRepository.save(order);
		logging.info("Creada " + order);
		
		return order;
				
	}
	
	public Orders updateOrder(String id, StatusOrderRequest orderRequest) throws StatusOrdersException {
		
		Orders order = getOrder(id);
		validateStatus(order.getStatus(), obtenStatus(orderRequest.getStatus()));
		order.setStatus(obtenStatus(orderRequest.getStatus()));
		order.setUpdatedAt(LocalDateTime.now());
		
		order = orderRepository.save(order);
		logging.info("Actualizada " + order);
		
		return order;
				
	}
	
	private void validateStatus(Status status, Status status2) throws StatusOrdersException {
		
		if(status2.toString().equals("CREATED")
				|| status2.toString().equals("CANCELLED") && !status.toString().equals("CREATED")
				|| status2.toString().equals("IN_TRANSIT") && !status.toString().equals("CREATED")
				|| status2.toString().equals("DELIVERED") && !status.toString().equals("IN_TRANSIT")) {
			
			throw new StatusOrdersException("El status " + status2.toString() 
									+ " no puede actualizar a " + status.toString());
		}
	}

	public Orders getOrder(String id) {
		
		return orderRepository.findById(java.util.UUID.fromString(id)).get();
	}
	
	public List<Orders> getSelectOrders(String select, String value) throws OrdersException, StatusOrdersException {
		
		switch(select) {
			case "status":
				return orderRepository.findByStatus(obtenStatus(value));
			case "origen":
				return orderRepository.findByOrigin(value);
			case "destino":
				return orderRepository.findByDestination(value);
			default:
				throw new OrdersException("El criterio no es reconocido");		
		}
	}

	private Status obtenStatus(String value) throws StatusOrdersException {
		
		switch(value) {
			case "CREATED":
				return Status.CREATED;
			case "IN_TRANSIT":
				return Status.IN_TRANSIT;
			case "DELIVERED":
				return Status.DELIVERED;
			case "CANCELLED":
				return Status.CANCELLED;
			default:
				throw new StatusOrdersException("El status no es reconocido");	
		}
	}
	
}
