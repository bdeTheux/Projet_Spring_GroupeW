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

    @GetMapping
    List<Basket> findAllByUserId(@RequestParam(required = false)int userId);

    @PostMapping("/basket")
    void createBasket(@RequestBody Basket Basket);

    @GetMapping("/basket/{id}")
    Basket findById(@PathVariable("id") int id);

    @PutMapping("/basket/{id}")
    void updateQuantity(@PathVariable("id") int id, @RequestBody Basket Basket);

    @DeleteMapping("/candies/{id}")
    void deleteBasket(@PathVariable("id") int id);
}
