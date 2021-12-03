package Frontend.FrontendBasket.proxies;

import Frontend.FrontendBasket.model.Basket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "BackendBasket-API",url="http://localhost:8000/")
public interface FrontendBasketProxy {
    //TODO
    //RequestHeader => cookies ..
    @GetMapping("/Basket")
    Iterator<Basket> findAllByUserId(@RequestParam() int userId );

    @GetMapping("/Basket")
    Object findProductDetails(@RequestParam() int productId);

    @GetMapping("/Basket/{id}")
    Basket findById(@PathVariable("id") int id);

    @PostMapping("/Basket")
    void createBasket (Basket basket);

    @DeleteMapping("/Basket/{id}")
    void deleteProduct(@PathVariable("id") int id);

    @PutMapping("/Basket/{id}")
    void updateQuantity(@PathVariable("id") int id  , @RequestParam() int nquantity);

    @GetMapping("/Basket/paid")
    void payBasket();




}
