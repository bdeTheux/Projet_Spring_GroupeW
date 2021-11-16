package Frontend.FrontendBasket.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "BackendBasket-API",url = "localhost:9000")
public interface FrontendBasketProxy {



}
