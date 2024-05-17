package sacola.api.sacola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sacola.api.sacola.model.User;
import sacola.api.sacola.repository.UserRepository;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
