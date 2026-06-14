package mx.ait.transportorders.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.dto.AssignmentRequest;
import mx.ait.transportorders.model.Assignment;
import mx.ait.transportorders.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
@RequiredArgsConstructor
public class AssignmentController {
    
    private final AssignmentService assignmentService;
    
    @PostMapping(value = "/create")
    public ResponseEntity<Assignment> create(@RequestParam UUID idOrder, @RequestParam UUID idDriver,
    		@RequestParam MultipartFile file, @RequestParam MultipartFile image) throws IOException{
    	
    	AssignmentRequest request = AssignmentRequest.builder().idDriver(idDriver).idOrder(idOrder)
    							.file(file).image(image).build();
    	
        return ResponseEntity.ok(assignmentService.createAssignment(request));
    }
    
}
