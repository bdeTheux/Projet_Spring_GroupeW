package Frontend.FrontendListProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FrontendListProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendListProductApplication.class, args);
	}

}
