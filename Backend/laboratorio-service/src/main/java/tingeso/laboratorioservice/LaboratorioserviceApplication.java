package tingeso.laboratorioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LaboratorioserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioserviceApplication.class, args);
	}

}
