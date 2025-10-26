package sacola.api.sacola.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Product product;
    private Integer quantidade;
    @ManyToOne
    @JsonIgnore
    private Sacola sacola;
}
