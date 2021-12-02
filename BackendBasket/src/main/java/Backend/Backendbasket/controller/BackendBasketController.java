package Backend.Backendbasket.controller;

import Backend.Backendbasket.model.BackendBasket;
import Backend.Backendbasket.service.BackendBasketService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/Basket")
public class BackendBasketController {

    private BackendBasketService service;

    public BackendBasketController(BackendBasketService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<BackendBasket> findAllByUserId(@RequestParam() int userId) {
        return service.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public BackendBasket findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> addBasket (@RequestBody  BackendBasket basket){
        BackendBasket bas = service.saveBasket(basket);
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
        BackendBasket nBasket = new BackendBasket();
        nBasket.setQuantity(nQuantity);
        service.updateQuantity( id,nBasket);
        System.out.println("update get " + service.findById(id));
        return "update";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBasket (@PathVariable("id") int id){
        System.out.println("delete " + service.findById(id));
        service.deleteProduct(id);
        return "delete";
    }
}
