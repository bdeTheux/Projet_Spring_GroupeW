package Frontend.FrontendBasket.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Component
@FeignClient(name = "candiesB",url = "localhost:8000")
public interface UserProxy {
}
