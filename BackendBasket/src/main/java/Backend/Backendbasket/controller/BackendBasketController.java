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

    @PutMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") int id, @RequestBody Basket bas ) {
        if (bas.getQuantity()<1) {
            deleteProduct(id);
        }else {
            service.updateQuantity(id, bas);
            System.out.println("update get " + service.findById(id));
        }
        return "update";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        System.out.println("delete " + service.findById(id));
        service.deleteProduct(id);
        return "delete";
    }
    @GetMapping("/paid")
    public void payBasket(@RequestParam int userId){
        service.paid(userId);

    };
}
