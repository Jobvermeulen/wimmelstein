package nl.inholland.service;

import nl.inholland.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GuitarshopService {

    List<Guitar> guitars = new ArrayList<>(
            Arrays.asList(
                    new Guitar("ft", "Fender", "Telecaster"),
                    new Guitar("fs", "Fender", "Stratocaster"),
                    new Guitar("gl", "Gibson", "Les Paul")
            )
    );

    public List<Guitar> getGuitars() {
        return guitars;
    }

    public Guitar getGuitar(String id) {
        for (Guitar guitar : guitars) {
            if (guitar.getId().equals(id)) {
                return guitar;
            }
        }
        return null;
    }

    public void addGuitar(Guitar guitar) {
        guitars.add(guitar);
    }
}
