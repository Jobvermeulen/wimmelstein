package nl.inholland.controller;

import nl.inholland.model.Guitar;
import nl.inholland.service.GuitarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guitars")
public class GuitarController {

    private GuitarService service;

    public GuitarController(GuitarService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Guitar> getAllGuitars() {
        return service.getAllGuitars();
    }

    @PostMapping(value = "")
    public void addGuitar(@RequestBody Guitar guitar) {
        service.addGuitar(guitar);
    }

    @RequestMapping(value = "{brand}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Guitar> getGuitarsByBrand(@PathVariable String brand) {
        System.out.println(brand);
        return service.getGuitarsByBrand(brand);
    }

    @RequestMapping(value = "{id}/value", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int getStockValueById(@PathVariable long id) {
        return service.getStockValue(id);
    }

}
