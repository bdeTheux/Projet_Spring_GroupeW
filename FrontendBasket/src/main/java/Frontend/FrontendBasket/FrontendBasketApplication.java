package Frontend.FrontendBasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FrontendBasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendBasketApplication.class, args);
	}

}
