package cookbook.persistence.repository;

import cookbook.persistence.entity.Cook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CookRepository extends CrudRepository<Cook, Long> {
    Optional<Cook> findByUsername(String username);
}
