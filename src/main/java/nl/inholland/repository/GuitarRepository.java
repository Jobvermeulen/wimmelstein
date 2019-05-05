package nl.inholland.repository;

import nl.inholland.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends CrudRepository<Guitar, Long> {

    Iterable<Guitar> getGuitarsByBrandOrderByPriceAsc(String brand);

}
