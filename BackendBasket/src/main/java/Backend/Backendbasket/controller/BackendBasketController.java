package Backend.Backendbasket.controller;

import Backend.Backendbasket.model.Basket;
import Backend.Backendbasket.service.BasketService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/basket")
public class BackendBasketController {

    private BasketService service;

    public BackendBasketController(BasketService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<Basket> findAllByUserId(@RequestParam() int userId) {
        return service.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public Basket findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> createBasket(@RequestBody Basket basket){
        Basket bas = service.createBasket(basket);
        if(bas == null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bas.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public String putBasket(@PathVariable("id") int id, @RequestParam() int nQuantity ) {
        Basket nBasket = new Basket();
        nBasket.setQuantity(nQuantity);
        service.updateQuantity( id,nBasket);
        System.out.println("update get " + service.findById(id));
        return "update";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        System.out.println("delete " + service.findById(id));
        service.deleteProduct(id);
        return "delete";
    }
    @GetMapping("/paid")
    public void payBasket(@RequestParam() int userId){
        service.paid(userId);

    };
}
