package sacola.api.sacola.model;


import org.springframework.data.relational.core.sql.In;

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
    
    
    public User(Long id,String name, String email, String password, Integer number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String setName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword() {
        return password;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer setNumber() {
        return number;
    }
    
    
}
