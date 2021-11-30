package Frontend.FrontendListProduct.proxy;

import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="candies", url="http://localhost:8000/")
public interface CandyProxy {

    @GetMapping("/candies")
    List<Candy> findAll(@RequestParam(required=false)String category, @RequestParam(required=false)Integer min, @RequestParam(required=false)Integer max);
    @GetMapping
    Iterable<Candy>findAllByCategory(@RequestParam(required = false) Category category);

    @GetMapping("/candies/{id}")
    Candy findById(@PathVariable("id") int id);

    @PutMapping("/candies/{id}")
    void updateCandy(@PathVariable("id") int id, @RequestBody Candy candy);

    @PostMapping("/candies")
    void createCandy(@RequestBody Candy candy);

    @DeleteMapping("/candies/{id}")
    void deleteCandy(@PathVariable("id") int id);

    // ???
    @PostMapping
    void saveCandy(@RequestBody Candy candy);

    @PostMapping
    List<Category> getCategories();
}
