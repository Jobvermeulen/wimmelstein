package nl.inholland.config;

import nl.inholland.model.ApiKey;
import nl.inholland.model.Guitar;
import nl.inholland.model.Stock;
import nl.inholland.repository.ApiKeyRepository;
import nl.inholland.repository.GuitarRepository;
import nl.inholland.repository.StockRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private GuitarRepository guitarRepository;
    private StockRepository stockRepository;
    private ApiKeyRepository keyRepository;

    public MyApplicationRunner(GuitarRepository guitarRepository, StockRepository stockRepository, ApiKeyRepository keyRepository) {
        this.guitarRepository = guitarRepository;
        this.stockRepository = stockRepository;
        this.keyRepository = keyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Files.lines(Paths.get("src/main/resources/guitars.csv"))
                .forEach(
                        line -> guitarRepository.save(
                                new Guitar(line.split(",")[0],
                                        line.split(",")[1],
                                        Integer.parseInt(line.split(",")[2]))
                        ));

        guitarRepository.findAll()
                .forEach(System.out::println);

        List<Guitar> guitars = (List<Guitar>) guitarRepository.findAll();
        guitars.stream()
                .forEach(a -> stockRepository.save(new Stock(a, new Random().nextInt(10))));

        stockRepository.findAll()
                .forEach(System.out::println);

        for (String s : Arrays.asList(new String[]{"5962A7199EBCA21A48ABAE8E8921A", "A21896CC68CF6822A8F4A9EC2D6A8", "57CB8558ADF9CE22FEE4DF2A34B86"})) {
            keyRepository.save(new ApiKey(s));
        }

        keyRepository.findAll()
                .forEach(System.out::println);


    }
}
