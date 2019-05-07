package nl.inholland.config;

import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private GuitarRepository guitarRepository;
    private StockRepository stockRepository;

    public MyApplicationRunner(GuitarRepository guitarRepository, StockRepository stockRepository) {
        this.guitarRepository = guitarRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
         * Read in file guitars.csv and parse each line, and create a guitar from it
         */
        Path path = Paths.get("src/main/resources/guitars.csv");
        Files.lines(path)

                .forEach(
                        line -> guitarRepository.save(new Guitar(line))
                );


        /**
         * Stream the guitars, and create an entry in stock
         */
        List<Guitar> guitars = (List<Guitar>) guitarRepository.findAll();
        guitars.stream()
                .map(Guitar::getId)
                .forEach(a -> stockRepository.save(new Stock(a, new Random().nextInt(10))));


        /**
         * Print out the stock
         */
        stockRepository.findAll()
                .forEach(System.out::println);
    }

}
