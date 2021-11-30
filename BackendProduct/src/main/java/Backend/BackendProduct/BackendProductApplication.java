package Backend.BackendProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaServer

@SpringBootApplication
@EnableEurekaClient
public class BackendProductApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendProductApplication.class, args);
	}

}
