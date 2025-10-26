package sacola.api.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@Table(name = "lojas")
public class Loja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String cnpj;
    
    private String endereco;
    private String telefone;
    private String email;
    
    @Builder.Default 
    @Column(nullable = false)
    private Boolean ativa = true;
    
    @Builder.Default 
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> produtos = new ArrayList<>();
    
    public void adicionarProduto(Product produto) {
        produtos.add(produto);
        produto.setLoja(this);
    }
    
    public void removerProduto(Product produto) {
        produtos.remove(produto);
        produto.setLoja(null);
    }
}