package nl.inholland.repository;

import nl.inholland.model.Guitar;
import org.springframework.data.repository.CrudRepository;

public interface GuitarRepository extends CrudRepository<Guitar, Long> {
}
