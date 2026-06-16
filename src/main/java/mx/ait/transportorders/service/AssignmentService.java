package mx.ait.transportorders.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.AssignmentRequest;
import mx.ait.transportorders.model.Assignment;
import mx.ait.transportorders.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {
	
	private final AssignmentRepository assignmentRepository;
	
	public Assignment createAssignment(AssignmentRequest assignmentRequest) throws IOException {
		
		Assignment assignment = Assignment.builder().idOrder(assignmentRequest.getIdOrder())
						.idDriver(assignmentRequest.getIdDriver())
						.file(assignmentRequest.getFile()!=null?assignmentRequest.getFile().getBytes():null)
						.image(assignmentRequest.getImage()!=null?assignmentRequest.getImage().getBytes():null)
						.build();
		
		return assignmentRepository.save(assignment);
				
	}
	
}
