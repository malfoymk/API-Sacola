package sacola.api.sacola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sacola.api.sacola.model.User;
import sacola.api.sacola.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password) {

        Optional<User> user = userService.authenticate(email, password);

        if (user.isPresent()) {
            // Retorna o usuário sem a senha para segurança
            User responseUser = user.get();
            responseUser.setPassword(null); // Não retorna a senha
            return ResponseEntity.ok(responseUser);
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }

    @GetMapping("/check-cpf")
    public ResponseEntity<Boolean> checkCpfExists(@RequestParam String cpf) {
        return ResponseEntity.ok(userService.cpfExists(cpf));
    }
}