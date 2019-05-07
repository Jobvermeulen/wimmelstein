package nl.inholland.repository;

import nl.inholland.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query("select s.quantity * g.price from Stock s, Guitar g where s.guitar.id = g.id and s.guitar.id = ?1")
    public int getStockValueByGuitarId(Long id);
}
