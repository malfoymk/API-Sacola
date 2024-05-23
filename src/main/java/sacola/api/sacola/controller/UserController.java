package sacola.api.sacola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sacola.api.sacola.exception.CpfNotFoundException;
import sacola.api.sacola.model.User;
import sacola.api.sacola.service.UserDetailsService;

@Tag(name = "1.End Points de Usuário")
@RestController
@RequestMapping("/sacola/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/sing-up")
    public ResponseEntity<String> register(@Validated @RequestBody User user) {
        try {
            if (userDetailsService.findByCpf(user.getCpf()) != null) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }
    } catch (CpfNotFoundException e) {

    }   if (userDetailsService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCpf(user.getCpf());
        newUser.setNumber(user.getNumber());

        userDetailsService.save(newUser);

        return ResponseEntity.ok().body("Usuário cadastrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody User user) {
        User foundUser = userDetailsService.findByEmail(user.getEmail());
        if (foundUser == null) {
            return ResponseEntity.badRequest().body("Email não cadastrado");
        }

        if (!foundUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha inválida");
        }

        return ResponseEntity.ok().body("Usuário logado com sucesso");
    }
}
