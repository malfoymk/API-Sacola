package sacola.api.sacola.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sacola.api.sacola.model.User;
import sacola.api.sacola.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Verifica se email já existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Verifica se CPF já existe
        if (userRepository.findByCpf(user.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }

        // Criptografa a senha
        user.setPasswordEncoder(passwordEncoder);
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.checkPassword(password, passwordEncoder)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean cpfExists(String cpf) {
        return userRepository.findByCpf(cpf).isPresent();
    }
}