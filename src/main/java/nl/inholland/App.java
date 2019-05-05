package nl.inholland;

import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Hello world!
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(GuitarRepository repository, StockRepository stockRepository) {
        return atStartup -> {

            Path path = Paths.get("src/main/resources/guitars.csv");
            Files.lines(path)

                    .forEach(
                            line -> repository.save(new Guitar(line))
                    );

            Stream.of(1,2,3,4,5)
                    .map(i -> new Stock(i, new Random().nextInt(10) ))
                    .forEach(a -> stockRepository.save(a));

            stockRepository.findAll()
                    .forEach(System.out::println);
        };



    }
}