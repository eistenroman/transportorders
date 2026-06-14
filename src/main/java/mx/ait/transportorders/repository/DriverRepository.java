package mx.ait.transportorders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ait.transportorders.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, java.util.UUID> {
	
    Optional<Driver> findByLicenseNumber(String licenseNumber);
    List<Driver> findByActive(boolean active);
    
}
