package Backend.Backendbasket.controller;

import Backend.Backendbasket.model.BackendBasket;
import Backend.Backendbasket.service.BackendBasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Basket")
public class BackendBasketController {

    private BackendBasketService service;

    public BackendBasketController(BackendBasketService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<BackendBasket> findAllByUserId(@RequestParam(required = false) int userId) {
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
    public void putBasket(@PathVariable("id") int id, @RequestParam() int nQuantity) {

        service.updateQuantity(id, nQuantity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBasket (@PathVariable("id") int id){
        service.deleteProduct(id);
    }

    @GetMapping("/paid")
    void payBasket(@RequestParam()  int userId){
        service.payBasket( userId);
    }
}
