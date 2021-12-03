package Frontend.FrontendBasket.proxies;


import Frontend.FrontendBasket.model.Candy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "candiesB",url = "http://localhost:9000/")
public interface ProductProxy {
    @GetMapping("/{id}")
    Candy findById(@PathVariable("id") int id);
}
