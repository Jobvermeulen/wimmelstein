package nl.inholland.controller;

import nl.inholland.service.GuitarshopService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    private GuitarshopService service;

    public StockController(GuitarshopService service) {
        this.service = service;
    }

    @RequestMapping(value = "value/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int getStockValueByGuitarId(@PathVariable long id) {
        return service.getStockValueByGuitarId(id);
    }
}
