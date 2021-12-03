package Frontend.FrontendBasket.proxies;


import Frontend.FrontendBasket.model.Candy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "candiesB",url = "http://localhost:8000/")
public interface ProductProxy {
    @GetMapping("candies/{id}")
    Candy findById(@PathVariable("id") int id);
}
