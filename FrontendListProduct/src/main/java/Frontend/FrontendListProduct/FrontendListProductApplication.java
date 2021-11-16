package Frontend.FrontendListProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FrontendListProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendListProductApplication.class, args);
	}

}
