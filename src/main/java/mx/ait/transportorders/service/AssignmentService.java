package mx.ait.transportorders.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.request.AssignmentRequest;
import mx.ait.transportorders.dto.response.AssignmentResponse;
import mx.ait.transportorders.model.Assignment;
import mx.ait.transportorders.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {
	
	private Log logging = LogFactory.getLog(AssignmentService.class);
	
	private final AssignmentRepository assignmentRepository;
	
	public AssignmentResponse createAssignment(AssignmentRequest assignmentRequest) throws IOException {
		
		Assignment assignment = Assignment.builder().idOrder(assignmentRequest.getIdOrder())
						.idDriver(assignmentRequest.getIdDriver())
						.file(assignmentRequest.getFile()!=null?assignmentRequest.getFile().getBytes():null)
						.image(assignmentRequest.getImage()!=null?assignmentRequest.getImage().getBytes():null)
						.build();
		
		assignment = assignmentRepository.save(assignment);
		logging.info("Asignacion Creado " + assignment);
		
		AssignmentResponse response = AssignmentResponse.builder().id(assignment.getId())
				.idOrder(assignment.getIdOrder()).idDriver(assignment.getIdDriver()).build();
		
		return response;
				
	}
	
}
