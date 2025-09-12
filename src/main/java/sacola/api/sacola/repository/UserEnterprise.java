package sacola.api.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import sacola.api.sacola.model.User;

@Repository
public interface UserEnterprise extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByCnpj(String cnpj);
}