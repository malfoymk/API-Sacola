package sacola.api.sacola.model;

import sacola.api.sacola.enumeration.Payment;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity

public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "sacola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;

    private boolean fechada;

    @Enumerated(EnumType.STRING)
    private Payment formaPagamento;

}
