package Frontend.FrontendListProduct.proxy;

import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name="candyAPI", url="localhost:9001")
public interface CandyProxy {

    @GetMapping
    Iterable<Candy>findAll();
    @GetMapping
    Iterable<Candy>findAllByCategory(@RequestParam(required = false) Category theme);

    @GetMapping("/candies/{id}")
    Candy findById(@PathVariable("id") int id);

    @PutMapping("/candies/{id}")
    void updateCandy(@PathVariable("id") int id, @RequestBody Candy candy);

    @PostMapping("/candies")
    void createCandy(@RequestBody Candy candy);

    @DeleteMapping("/candies/{id}")
    void deleteCandy(@PathVariable("id") int id);
}
