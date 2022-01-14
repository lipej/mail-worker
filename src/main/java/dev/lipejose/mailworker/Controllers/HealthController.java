package dev.lipejose.mailworker.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "I'm alive!");

        return map;
    }
}
