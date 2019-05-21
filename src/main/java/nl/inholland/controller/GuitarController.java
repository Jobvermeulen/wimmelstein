package nl.inholland.controller;

import nl.inholland.model.Guitar;
import nl.inholland.service.GuitarshopService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addGuitar(@RequestBody Guitar guitar) {
        service.addGuitar(guitar);
        return String.valueOf(guitar.getId());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteGuitar(@PathVariable long id) {
        service.deleteGuitar(id);
    }
}
