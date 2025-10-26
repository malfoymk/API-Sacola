package sacola.api.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sacola.api.sacola.model.Loja;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEnterprise extends JpaRepository<Loja, Long> {
    
    Optional<Loja> findByCnpj(String cnpj);
    Optional<Loja> findByNome(String nome);
    boolean existsByCnpj(String cnpj);
    boolean existsByNome(String nome);
    List<Loja> findByAtivaTrue();
}