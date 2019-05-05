package nl.inholland.repository;

import nl.inholland.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query("SELECT g.price * s.quantity from Guitar g, Stock s where g.id = ?1 " +
            "and s.id = (select s.id from Stock s where s.guitarId = ?1)")
    public int getStockValueById(long id);
}
