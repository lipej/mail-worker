package dev.lipejose.mailworker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.lipejose.mailworker.model.entity.HealthResponse;

@RestController
public class HealthController {

    @RequestMapping(value = "/health", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<HealthResponse> health() {
        HealthResponse response = new HealthResponse();

        response.setMessage("Everything is fine!");
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
}
