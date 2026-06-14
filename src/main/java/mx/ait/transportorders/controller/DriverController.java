package mx.ait.transportorders.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.CreateDriverRequest;
import mx.ait.transportorders.model.Driver;
import mx.ait.transportorders.service.DriverService;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    
	private Log logging = LogFactory.getLog(DriverController.class);
	
    private final DriverService driverService;
    
    @PostMapping(value = "/create")
    public ResponseEntity<Driver> create(@Valid @RequestBody CreateDriverRequest request){
    	
    	logging.info("Creando " + request);
    	
        return ResponseEntity.ok(driverService.createDriver(request));
    }
    
    
    @GetMapping(value = "/drivers")
    public ResponseEntity<List<Driver>> getAll(){
    	
    	return ResponseEntity.ok(driverService.getActiveDrivers());
    }
    
}
