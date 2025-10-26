package sacola.api.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sacola.api.sacola.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByLojaId(Long lojaId);

    List<Product> findByDisponivelTrue();

    List<Product> findByNomeContainingIgnoreCase(String nome);

    List<Product> findByLojaIdAndDisponivelTrue(Long lojaId);
}