package Backend.Backendbasket.controller;

import Backend.Backendbasket.model.BackendBasket;
import Backend.Backendbasket.service.BackendBasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Basket")
public class BackendBasketController {

    private BackendBasketService service;

    public BackendBasketController(BackendBasketService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<BackendBasket> findAllById_user(@RequestParam() int id_user) {
        return service.findAllById_user(id_user);
    }
    @GetMapping("/{id}")
    public BackendBasket findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PostMapping()
    public void addBasket (BackendBasket basket){ service.saveBasket(basket);}

    @PutMapping("/{id}")
    public void putBasket(@PathVariable("id") int id, @RequestParam() int nQuantity) {
        service.updateQuantity(id, nQuantity);
    }

    @DeleteMapping("/{id}")
    public void deleteBasket (@PathVariable("id") int id){
        service.deleteProduct(id);
    }
    @GetMapping()
    void payBasket(){
        service.payBasket();
    };
}
