package mx.com.ismaeloe.feign_shopping_service.feignclient;

import mx.com.ismaeloe.customer.entity.Customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

	final static String CONTEXT = "/customers";
	
    @GetMapping(value = CONTEXT + "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
