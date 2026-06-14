package mx.ait.transportorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ait.transportorders.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, java.util.UUID> {
	
}
