package mx.ait.transportorders.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.ait.transportorders.model.Orders;
import mx.ait.transportorders.model.Status;

public interface OrderRepository extends JpaRepository<Orders, java.util.UUID> {
	
	List<Orders> findByStatus(Status status);
	List<Orders> findByOrigin(String origin);
	List<Orders> findByDestination(String destination);
	
	@Query("SELECT o FROM Orders o WHERE FUNCTION('DATE', o.createdAt) = :fecha")
    List<Orders> findByFechaOnly(@Param("fecha") LocalDate fecha);
}
