package nl.inholland.controller;

import nl.inholland.model.Guitar;
import nl.inholland.service.GuitarshopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guitars")
public class GuitarController {

    private GuitarshopService service;

    public GuitarController(GuitarshopService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Guitar> getAllGuitars() {
        return service.getAllGuitars();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGuitar(@RequestBody Guitar guitar) {
        service.addGuitar(guitar);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteGuitar(@PathVariable long id) {
        service.deleteGuitar(id);
    }
}
