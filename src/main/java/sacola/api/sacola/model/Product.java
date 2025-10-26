package sacola.api.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // Correção: "Json" com J maiúsculo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*; // Import JPA correto
import jakarta.validation.constraints.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long em vez de long para entidades JPA
    
    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @Positive(message = "Valor unitário deve ser positivo")
    @DecimalMin(value = "0.01", message = "Valor unitário deve ser no mínimo 0.01")
    @Column(name = "valor_unitario", nullable = false)
    private double valorUnitario;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean disponivel = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id", nullable = false)
    @JsonIgnore
    private Loja Loja;
    
    // Método para visualização segura
    public String getNomeLoja() {
        return Loja != null ? Loja.getNome() : null;
    }
    
    // Método para obter ID da loja
    public Long getLojaId() {
        return Loja != null ? Loja.getId() : null;
    }
}