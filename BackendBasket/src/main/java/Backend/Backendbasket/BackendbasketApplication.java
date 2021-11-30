package Backend.Backendbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackendbasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendbasketApplication.class, args);
	}

}
