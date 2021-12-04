package Frontend.FrontendDetailProduct.proxy;

import Frontend.FrontendDetailProduct.model.Basket;
import Frontend.FrontendDetailProduct.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="basketAPI", url="http://localhost:8002/")
public interface BasketProxy {

    @GetMapping("/basket")
    Iterable<Basket> findAllByUserId(@RequestHeader(name= "Authorization") String token,@RequestParam() int userId );

    @GetMapping("/basket")
    Object findProductDetails(@RequestParam() int productId);

    @GetMapping("/basket/{id}")
    Basket findById(@PathVariable("id") int id);

    @PostMapping("/basket")
    void createBasket (@RequestHeader(name= "Authorization") String token,Basket basket);

    @PutMapping("/basket/update/{id}")
    void updateQuantity(@RequestHeader(name= "Authorization") String token,@PathVariable("id") int id  ,@RequestBody Basket bas);

    @GetMapping("/basket/paid")
    void payBasket(@RequestHeader(name= "Authorization") String token,@RequestParam() int userId);
}
