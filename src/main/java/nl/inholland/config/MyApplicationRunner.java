package nl.inholland.config;

import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
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
        Files.lines(Paths.get("guitars.csv"))
                .forEach(
                        line -> guitarRepository.save(
                                new Guitar(line.split(",")[0],
                                        line.split(",")[1],
                                        "../images/" + line.split(",")[2] + ".jpg",
                                        Integer.parseInt(line.split(",")[4]),
                                        line.split(",")[3])
                                        )
                );

        guitarRepository.findAll()
                .forEach(System.out::println);

        List<Guitar> guitars = (List<Guitar>) guitarRepository.findAll();
        guitars.stream()
                .forEach(a -> stockRepository.save(new Stock(a, new Random().nextInt(10))));

        stockRepository.findAll()
                .forEach(System.out::println);
    }


}
