package mx.ait.transportorders.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.ait.transportorders.model.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {
	
	private java.util.UUID id;
	private Status status;
	private String origin;
	private String destination;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
