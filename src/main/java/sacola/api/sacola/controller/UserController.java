package sacola.api.sacola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import sacola.api.sacola.model.User;
import sacola.api.sacola.service.UserDetailsService;

import java.util.Optional;

@Tag(name = "1. Endpoints de Usuário")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDetailsService userService; // ← Este é o nome do campo

    // Cadastro
    @PostMapping
    public ResponseEntity<?> register(@Validated @RequestBody User user) {
        if (userService.findByCpf(user.getCpf()).isPresent()) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        User savedUser = userService.save(user);
        savedUser.setPassword(null); // não retornar senha

        return ResponseEntity.ok(savedUser);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody User user) {
        Optional<User> foundUser = userService.findByEmail(user.getEmail());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Email não cadastrado");
        }

        if (!foundUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha inválida");
        }
        User loggedUser = foundUser.get();
        loggedUser.setPassword(null);

        return ResponseEntity.ok(loggedUser);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> getUserByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf) // ← Corrigido: userService em vez de userDetailsService
                .map(user -> {
                    user.setPassword(null); // segurança
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}