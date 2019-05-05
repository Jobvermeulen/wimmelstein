package nl.inholland.service;

import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class GuitarService {

    private GuitarRepository guitarRepository;
    private StockRepository stockRepository;

    public GuitarService(GuitarRepository guitarRepository, StockRepository stockRepository) {
        this.guitarRepository = guitarRepository;
        this.stockRepository = stockRepository;
    }

    public Iterable<Guitar> getAllGuitars() {
        return guitarRepository.findAll();
    }

    public Iterable<Guitar> getGuitarsByBrand(String brand) {
        return guitarRepository.getGuitarsByBrandOrderByPriceAsc(brand);
    }

    public void addGuitar(Guitar guitar) {
        guitarRepository.save(guitar);
    }

    public int getStockValue(long id) {
        return stockRepository.getStockValueById(id);
    }

    public Iterable<Stock> getGuitarsInStockMinimumOf(int min) {
        return stockRepository.getAllByQuantityGreaterThanEqualOrderByQuantityDesc(min);
    }
}
