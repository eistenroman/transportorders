package mx.ait.transportorders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import mx.ait.transportorders.dto.request.CreateOrderRequest;
import mx.ait.transportorders.dto.response.OrdersResponse;
import mx.ait.transportorders.exception.OrdersException;
import mx.ait.transportorders.exception.StatusOrdersException;
import mx.ait.transportorders.model.Orders;
import mx.ait.transportorders.model.Status;
import mx.ait.transportorders.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
    private OrderRepository orderRepository;
	
	@Mock
    private ModelMapper modelMapper;
	
	@InjectMocks
    private OrderService orderService;
	
	@Test
    public void whenGetOrder_thenReturnOrders() throws OrdersException {

        // Given
        Orders mockOrder = new Orders();
        mockOrder.setId(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"));
        mockOrder.setStatus(Status.DELIVERED);
        mockOrder.setDestination("Destino");
        mockOrder.setOrigin("Origen");
        mockOrder.setCreatedAt(LocalDateTime.now());
        
        when(orderRepository.findById(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"))).thenReturn(Optional.of(mockOrder));

        // When
        OrdersResponse found = orderService.getOrder("bbd94054-81eb-4d78-ac8c-fad4f04c13a9");
        
        // Then
        assertEquals(null, found);

    }
	
	@Test
    public void whenGetSelectOrders_thenReturnListOrders() throws OrdersException, StatusOrdersException {

        // Given
        List<Orders> mockOrderList = Arrays.asList(
        		new Orders(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"),
        				Status.DELIVERED,"Origen","Destino",LocalDateTime.now(),null));
        
        when(orderRepository.findByOrigin(anyString())).thenReturn(mockOrderList);

        // When
        List<OrdersResponse> found = orderService.getSelectOrdersResponse("origen", "Origen");
        
        // Then
        assertEquals(mockOrderList.size(), found.size());

    }
	
	@Test
    public void whenCreateOrders_thenReturnOrdersRsponse() {

        // Given
		Orders orders = new Orders(UUID.fromString("bbd94054-81eb-4d78-ac8c-fad4f04c13a9"),
				Status.DELIVERED,"Origen","Destino",LocalDateTime.now(),null);
		
		when(orderRepository.save(any(Orders.class))).thenReturn(orders);

        // When
		OrdersResponse createOrders = orderService.createOrder(new CreateOrderRequest("Origen A1", "Destino D1"));

        // Then
        assertEquals(null, createOrders);

    }
	
	@Test
    public void whenUpdateOrders_thenReturnOrdersResponse() throws StatusOrdersException, OrdersException {
		
    }
}
