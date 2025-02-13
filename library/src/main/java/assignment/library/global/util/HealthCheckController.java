package assignment.library.global.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.status(OK).build();
    }
}
