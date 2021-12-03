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
    @GetMapping("/basket")
    Iterator<Basket> findAllByUserId(@RequestParam() int userId );

    @GetMapping("/basket")
    Object findProductDetails(@RequestParam() int productId);

    @GetMapping("/basket/{id}")
    Basket findById(@PathVariable("id") int id);

    @PostMapping("/basket")
    void createBasket (Basket basket);

    @DeleteMapping("/basket/{id}")
    String deleteProduct(@PathVariable("id") int id);

    @PutMapping("/basket/{id}")
    void updateQuantity(@PathVariable("id") int id  , @RequestParam() int nquantity);

    @GetMapping("/basket/paid")
    void payBasket(int userId);




}
