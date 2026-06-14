package mx.ait.transportorders.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.CreateOrderRequest;
import mx.ait.transportorders.dto.StatusOrderRequest;
import mx.ait.transportorders.exception.OrdersException;
import mx.ait.transportorders.exception.StatusOrdersException;
import mx.ait.transportorders.model.Orders;
import mx.ait.transportorders.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    
	private Log logging = LogFactory.getLog(DriverController.class);
	
    private final OrderService orderService;
    
    @PostMapping(value = "/create")
    public ResponseEntity<Orders> create(@Valid @RequestBody CreateOrderRequest request){
    	
    	logging.info("Creando " + request);
    	
        return ResponseEntity.ok(orderService.createOrder(request));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Orders> update(@PathVariable String id, 
    		@Valid @RequestBody StatusOrderRequest request) throws StatusOrdersException{
    	
    	return ResponseEntity.ok(orderService.updateOrder(id, request));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Orders> get(@PathVariable String id){
    	
    	return ResponseEntity.ok(orderService.getOrder(id));
    }
    
    @GetMapping(value = "/{select}/{value}")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable String select, @PathVariable String value) 
    		throws OrdersException, StatusOrdersException{
    	
    	return ResponseEntity.ok(orderService.getSelectOrders(select, value));
    }
    
}
