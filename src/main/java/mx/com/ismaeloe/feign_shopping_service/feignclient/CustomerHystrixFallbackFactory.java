package mx.com.ismaeloe.feign_shopping_service.feignclient;

import mx.com.ismaeloe.customer.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory  implements CustomerClient{

	@Override
    public ResponseEntity<Customer> getCustomer(long id) {
    
		/*With Lombook */
		Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        
		/*WithOut Lombook 
		Customer customer = new Customer();
		 */
		
        return ResponseEntity.ok(customer);
    }
}
