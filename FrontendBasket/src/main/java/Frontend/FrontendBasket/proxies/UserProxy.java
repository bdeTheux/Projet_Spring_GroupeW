package Frontend.FrontendBasket.proxies;
import Frontend.FrontendBasket.model.Candy;
import Frontend.FrontendBasket.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "usersB",url = "http://localhost:8001/")
public interface UserProxy {
    @GetMapping("/{id}")
    User getUser(@PathVariable("id") int id);
}
