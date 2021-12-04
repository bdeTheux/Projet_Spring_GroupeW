package grouped.usersfront.proxy;

import grouped.usersfront.loadBalancer.LoadBalancerConfig;
import grouped.usersfront.model.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@FeignClient(name="usersB", url="http://localhost:8001/")
//@LoadBalancerClient(name = "gateway", configuration = LoadBalancerConfig.class)
@RequestMapping("/users")
public interface UsersFrontProxy {

    @GetMapping
    List<User> findAll();

    @GetMapping("/{id}")
    User findById(@PathVariable("id") int id);

    @GetMapping("/email/{email}")
    User findByEmail(@PathVariable("email") String email);

    @PostMapping
    User createUser(@RequestBody User user);

    @PutMapping("/{id}")
    User updateUser(@PathVariable("id") int id, @RequestBody User user);

    @DeleteMapping("/{id}")
    User deleteUser(@PathVariable("id") int id);
}
