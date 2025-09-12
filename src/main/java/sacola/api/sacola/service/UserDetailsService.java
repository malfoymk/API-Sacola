package sacola.api.sacola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sacola.api.sacola.model.User;
import sacola.api.sacola.repository.UserRepository;
import sacola.api.sacola.exception.CpfNotFoundException;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User loadUserByCpf(String cpf) throws CpfNotFoundException {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new CpfNotFoundException("CPF não encontrado"));
    }
}
