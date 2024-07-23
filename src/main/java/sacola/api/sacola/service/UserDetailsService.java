package sacola.api.sacola.service;

import java.util.Deque;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sacola.api.sacola.model.User;
import sacola.api.sacola.repository.UserRepository;
import sacola.api.sacola.util.AccountOperation;
import sacola.api.sacola.exception.CpfNotFoundException;

@Service
public class UserDetailsService {
    
    private final Deque<AccountOperation> operationHistory = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

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

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }  

    public User loadUserByCpf(String cpf) throws CpfNotFoundException {
        User user = userRepository.findByCpf(cpf);
        if (user == null) {
            throw new CpfNotFoundException("CPF não encontrado");
        }
        return user;
    }
}

