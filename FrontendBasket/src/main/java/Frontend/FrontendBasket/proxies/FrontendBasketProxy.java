package Frontend.FrontendBasket.proxies;

import Frontend.FrontendBasket.model.Basket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "BackendBasket-API",url="http://localhost:8002/")
public interface FrontendBasketProxy {
    //TODO
    //RequestHeader => cookies ..
    @GetMapping("/basket")
    Iterable<Basket> findAllByUserId(@RequestParam() int userId );

    @GetMapping("/basket")
    Object findProductDetails(@RequestParam() int productId);

    @GetMapping("/basket/{id}")
    Basket findById(@PathVariable("id") int id);

    @PostMapping("/basket")
    void createBasket (Basket basket);

   // @GetMapping("/basket/delete/{id}")
    //String deleteProduct(@PathVariable("id") int id);

    @PutMapping("/basket/update/{id}")
    void updateQuantity(@PathVariable("id") int id  ,@RequestBody Basket bas);

    @GetMapping("/basket/paid")
    void payBasket(@RequestParam() int userId);




}
