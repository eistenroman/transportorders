package mx.ait.transportorders.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {
	
	private java.util.UUID id;
	private String name;
	private String licenseNumber;
	
}
