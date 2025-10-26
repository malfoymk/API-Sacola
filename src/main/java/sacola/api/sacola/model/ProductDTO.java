package sacola.api.sacola.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String nome;
    private double valorUnitario;
    private Boolean disponivel;
    private String nomeLoja;
    private Long lojaId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.nome = product.getNome();
        this.valorUnitario = product.getValorUnitario();
        this.disponivel = product.getDisponivel();
        this.nomeLoja = product.getNomeLoja();
        this.lojaId = product.getLojaId();
    }
}