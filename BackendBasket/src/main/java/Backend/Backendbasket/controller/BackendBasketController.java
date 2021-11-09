package Backend.Backendbasket.controller;

import Backend.Backendbasket.model.BackendBasket;
import Backend.Backendbasket.service.BackendBasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BackendBasketController {

    private BackendBasketService service;

    @GetMapping("/{id_user}")
    public List<BackendBasket> getBasketByUser(@PathVariable("id_user") int id_user) {
        return service.findAll(id_user);
    }
    @GetMapping("/{id}")
    public BackendBasket getBasketById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public void putBasket(@PathVariable("id") int id, @RequestParam(required = true) int nQuantity) {
        service.updateQuantity(id, nQuantity);
    }

    @DeleteMapping("/{id}")
    public void deleteBasket (@PathVariable("id") int id){
        service.deleteProduct(id);
    }
    public BackendBasketController(BackendBasketService service) {
        this.service = service;
    }
}
