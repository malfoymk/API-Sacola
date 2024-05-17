package sacola.api.sacola.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import io.micrometer.core.ipc.http.HttpSender.Response;

public class UserController {


    @PostMapping
    public ResponseEntity register(@Validated @RequestBody User user) {
        return ResponseEntity.ok(user);
    }
    
}
