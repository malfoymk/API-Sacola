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
    
    public User(Long id, String name, String email, String password, Integer number, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        setCpf(cpf);  // Utilize o setter para validar o CPF
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
        this.cpf = cpf;
    }
}
