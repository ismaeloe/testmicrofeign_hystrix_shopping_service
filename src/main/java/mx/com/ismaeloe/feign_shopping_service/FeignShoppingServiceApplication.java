package mx.com.ismaeloe.feign_shopping_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * Caused by: org.apache.http.conn.HttpHostConnectException: Connect to localhost:8761 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect
 * First we need EurekaServer
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class FeignShoppingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignShoppingServiceApplication.class, args);
	}

}
