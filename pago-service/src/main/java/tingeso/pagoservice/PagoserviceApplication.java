package tingeso.pagoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PagoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagoserviceApplication.class, args);
	}

}
