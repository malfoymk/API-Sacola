package sacola.api.sacola.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity
public class User {

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
        throw new UnsupportedOperationException("O email já está cadastrado");
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

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        if (!password.matches(".*[@!#&*%$].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um dos seguintes caracteres especiais: '@', '!', '#', '&', '*', '%', '$'");
        }
        this.password = password;
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
