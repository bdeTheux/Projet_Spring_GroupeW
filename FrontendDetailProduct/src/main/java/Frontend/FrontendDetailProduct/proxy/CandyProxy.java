package Frontend.FrontendDetailProduct.proxy;

import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="candyAPI", url="http://localhost:8000/")
public interface CandyProxy {

    @GetMapping
    List<Candy> findAll();
    @GetMapping
    List<Candy>findAllByCategory(@RequestParam(required = false) Category theme);

    @GetMapping("/candies/{id}")
    Candy findById(@PathVariable("id") int id);

    @PutMapping("/candies/{id}")
    void updateCandy(@PathVariable("id") int id, @RequestBody Candy candy);

    @PostMapping("/candies")
    void createCandy(@RequestBody Candy candy);

    @DeleteMapping("/candies/{id}")
    void deleteCandy(@PathVariable("id") int id);
}
