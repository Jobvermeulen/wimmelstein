package nl.inholland.service;

import nl.inholland.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GuitarshopService {

    private List<Guitar> guitars = new ArrayList<>(
            Arrays.asList(
                    new Guitar("ft", "Fender", "Telecaster", 1450),
                    new Guitar("fs", "Fender", "Stratocaster", 1750),
                    new Guitar("gl", "Gibson", "Les Paul", 3000),
                    new Guitar("ij", "Ibanez", "Jem", 2500)
            )
    );

    public List<Guitar> getGuitars(boolean sorted, int minumum, int pageSize) {
        Stream<Guitar> guitarStream = guitars.stream()
                .filter(guitar -> guitar.getPrice() >=  minumum);

        if (sorted) {
            guitarStream = guitarStream
                    .sorted(Comparator.comparingInt(Guitar::getPrice));
        }

        return guitarStream
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Guitar getGuitar(String id) {
        return guitars.stream()
                .filter(guitar -> guitar.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addGuitar(Guitar guitar) {
        guitars.add(guitar);
    }

    public List<Guitar> getGuitarsByBrand(String brand) {
        return guitars.stream()
                .filter(guitar -> guitar.getBrand().equals(brand))
                .collect(Collectors.toList());
    }
}
