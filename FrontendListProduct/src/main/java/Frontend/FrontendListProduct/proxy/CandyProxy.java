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
    List<Candy> findAll(@RequestParam(required=false)Category category, @RequestParam(required = false)String order, @RequestParam(required=false)Double min, @RequestParam(required=false)Double max);
    @GetMapping("/candies")
    List<Candy>findAllByCategory(@RequestParam(required = false) Category category);

    @GetMapping("/candies/{id}")
    Candy findById(@PathVariable("id") int id);

    @PutMapping("/candies/{id}")
    void updateCandy(@PathVariable("id") int id, @RequestBody Candy candy, @RequestHeader(name = "Authorization")String token);

    /*@PostMapping("/candies")
    void createCandy(@RequestBody Candy candy);
     */

    @DeleteMapping("/candies/delete/{id}")
    void deleteCandy(@PathVariable("id") int id, @RequestHeader(name = "Authorization")String token);

    // ???
    @PostMapping("/candies")
    void saveCandy(@RequestBody Candy candy, @RequestHeader(name = "Authorization")String token);

    @PostMapping("/candies")
    List<Category> getCategories();
}
