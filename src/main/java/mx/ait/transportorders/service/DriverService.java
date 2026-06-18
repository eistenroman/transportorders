package mx.ait.transportorders.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.request.CreateDriverRequest;
import mx.ait.transportorders.dto.response.DriverResponse;
import mx.ait.transportorders.model.Driver;
import mx.ait.transportorders.repository.DriverRepository;

@Service
@RequiredArgsConstructor
public class DriverService {
	
	private Log logging = LogFactory.getLog(DriverService.class);
	
	private final DriverRepository driverRepository;
	
	private final ModelMapper modelMapper;
	
	public DriverResponse createDriver(CreateDriverRequest driverRequest) {
		
		Driver driver = Driver.builder().name(driverRequest.getName())
						.licenseNumber(driverRequest.getLicenseNumber())
						.active(true).build();
		
		driver =  driverRepository.save(driver);
		
		logging.info("Conductor creado " + driver);
		
		DriverResponse response = DriverResponse.builder().id(driver.getId())
				.name(driver.getName()).licenseNumber(driver.getLicenseNumber()).build();
		
		return response;
	}
	
	public List<DriverResponse> getActiveDrivers(){
		
		List<DriverResponse> drivers = new ArrayList<>();
		for(Driver driver: driverRepository.findByActive(true)) {
			
			drivers.add(modelMapper.map(driver, DriverResponse.class));
		}
		
		return drivers;
	}
}
