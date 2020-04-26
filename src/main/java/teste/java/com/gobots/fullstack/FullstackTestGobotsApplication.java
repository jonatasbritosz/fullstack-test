package teste.java.com.gobots.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class FullstackTestGobotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackTestGobotsApplication.class, args);
	}

}
