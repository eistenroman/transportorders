package mx.ait.transportorders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import mx.ait.transportorders.dto.request.CreateDriverRequest;
import mx.ait.transportorders.dto.response.DriverResponse;
import mx.ait.transportorders.model.Driver;
import mx.ait.transportorders.repository.DriverRepository;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
	
	@Mock
    private DriverRepository driverRepository;
	
	@Mock
    private ModelMapper modelMapper;
	
	@InjectMocks
    private DriverService driverService;
	
	@Test
    public void whenFindByGetActiveDrivers_thenReturnListDriverDriverResponse() {

        // Given
        List<Driver> listDriverMock = Arrays.asList(
        		new Driver(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),"Name","4563212", true));
        
        when(driverRepository.findByActive(true)).thenReturn(listDriverMock);

        // When
        List<DriverResponse> found = driverService.getActiveDrivers();

        // Then
        assertEquals(listDriverMock.size(), found.size());

    }
	
	@Test
    public void whenCreateDriver_thenReturnDriverResponse() {

        // Given
		Driver driver = new Driver(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),"Juan Gomez","4563212", true);
		
		when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        // When
		DriverResponse createDriver = driverService.createDriver(new CreateDriverRequest("Juan Gomez", "4563212"));

        // Then
        assertEquals(null, createDriver);

    }
}
