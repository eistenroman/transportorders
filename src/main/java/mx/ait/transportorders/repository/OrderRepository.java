package mx.ait.transportorders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ait.transportorders.model.Orders;
import mx.ait.transportorders.model.Status;

public interface OrderRepository extends JpaRepository<Orders, java.util.UUID> {
	
	List<Orders> findByStatus(Status status);
	List<Orders> findByOrigin(String origin);
	List<Orders> findByDestination(String destination);
	
}
