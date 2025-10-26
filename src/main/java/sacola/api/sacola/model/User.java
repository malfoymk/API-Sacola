package sacola.api.sacola.model;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Setter;

import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User implements UserDetails {

    
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    private String name;
    private String password;
    private Integer number;
    private String cpf;
    private String endereco;
    
    public User(Long id, String name, String email, String password, Integer number, String cpf, String endereco) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        setCpf(cpf);  // Utilize o setter para validar o CPF
        setEndereco(endereco);  // Utilize o setter para validar o endereço
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
         return email;
    }

    public void setEmail(String email) {
        if (email == null || email.toString().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
        }
        if (!email.contains("@")) {
           throw new IllegalArgumentException("O email não é válido");
       }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Transient
    private transient PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        if (!password.matches(".*[@!#&*%$].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um dos seguintes caracteres especiais: '@', '!', '#', '&', '*', '%', '$'");
        }

        if (passwordEncoder != null) {
            this.password = passwordEncoder.encode(password);
        } else {
            throw new IllegalStateException("PasswordEncoder não foi configurado devidamente!");
        }
    }

    public boolean checkPassword(String rawPassword, PasswordEncoder encoder){
        return encoder.matches(rawPassword, this.password);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!CPFValidator.isValidCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (!Address.isValidAddress(endereco)) {
            throw new IllegalArgumentException("Endereço inválido");
        }
        this.endereco = endereco;
}
}
