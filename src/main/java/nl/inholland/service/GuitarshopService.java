package nl.inholland.service;

import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class GuitarshopService {

    private GuitarRepository guitarRepository;
    private StockRepository stockRepository;

    public GuitarshopService(GuitarRepository guitarRepository, StockRepository stockRepository) {
        this.guitarRepository = guitarRepository;
        this.stockRepository = stockRepository;
    }

    public Iterable<Guitar> getAllGuitars() {
        return guitarRepository.findAll();
    }

    public void addGuitar(Guitar guitar) {
        guitarRepository.save(guitar);
    }

    public void deleteGuitar(long id) {
        guitarRepository.delete(guitarRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public int getStockValueByGuitarId(long id) {
        return stockRepository.getStockValueByGuitarId(id);
    }

    public void deleteStockItem(long id) {
        stockRepository.delete(stockRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public Iterable<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Guitar getGuitarById(long id) {
        return guitarRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
