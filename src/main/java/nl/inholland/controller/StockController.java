package nl.inholland.controller;

import nl.inholland.model.Stock;
import nl.inholland.service.GuitarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    private GuitarService service;

    public StockController(GuitarService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Stock> getGuitarsInStockMinimumOf(@RequestParam int minimum) {
        return service.getGuitarsInStockMinimumOf(minimum);
    }
}
