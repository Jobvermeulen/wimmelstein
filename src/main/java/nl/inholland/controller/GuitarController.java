package nl.inholland.controller;

import nl.inholland.model.Guitar;
import nl.inholland.service.GuitarshopService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guitars")
public class GuitarController {

    private GuitarshopService service;

    public GuitarController(GuitarshopService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Guitar> getAllGuitars(@RequestParam(required = false) boolean sorted,
                                      @RequestParam(required = false, defaultValue = "0") int minimum,
                                      @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE) int limit) {
        return service.getGuitars(sorted, minimum, limit);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Guitar getGuitarById(@PathVariable String id) {
        return service.getGuitar(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGuitar(@RequestBody Guitar guitar) {
        service.addGuitar(guitar);
    }
}
