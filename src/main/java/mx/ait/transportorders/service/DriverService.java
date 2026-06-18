package mx.ait.transportorders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.request.CreateDriverRequest;
import mx.ait.transportorders.model.Driver;
import mx.ait.transportorders.repository.DriverRepository;

@Service
@RequiredArgsConstructor
public class DriverService {
	
	private final DriverRepository driverRepository;
	
	public Driver createDriver(CreateDriverRequest driverRequest) {
		
		Driver driver = Driver.builder().name(driverRequest.getName())
						.licenseNumber(driverRequest.getLicenseNumber())
						.active(true).build();
		
		return driverRepository.save(driver);
				
	}
	
	public List<Driver> getActiveDrivers(){
		
		return driverRepository.findByActive(true);
	}
}
