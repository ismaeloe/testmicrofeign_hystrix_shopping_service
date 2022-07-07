package mx.com.ismaeloe.feign_shopping_service.feignclient;

import mx.com.ismaeloe.product.entity.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*Caused by: java.lang.IllegalArgumentException: @RequestMapping annotation not allowed on @FeignClient interfaces
 * @RequestMapping(value = "/products")
 */
@FeignClient(name = "product-service")
public interface ProductClient {

	final static String CONTEXT = "/products";
	
    @GetMapping(value = CONTEXT + "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @PutMapping (value = CONTEXT + "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(
    			@PathVariable  Long id ,
    			@RequestParam(name = "quantity", required = true) Double quantity);

   /*Method NOt Allowed 
    @GetMapping(value = CONTEXT + "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(
    		@PathVariable  Long id ,
    		@RequestParam(name = "quantity", required = true) Double quantity);
    }
    */
    
}