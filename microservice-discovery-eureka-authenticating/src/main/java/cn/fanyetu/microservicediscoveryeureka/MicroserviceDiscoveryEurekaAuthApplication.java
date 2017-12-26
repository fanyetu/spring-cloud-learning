package cn.fanyetu.microservicediscoveryeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 声明这是一个eureka server
@SpringBootApplication
public class MicroserviceDiscoveryEurekaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDiscoveryEurekaAuthApplication.class, args);
	}
}
