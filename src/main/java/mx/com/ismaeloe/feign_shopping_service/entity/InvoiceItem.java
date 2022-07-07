package mx.com.ismaeloe.feign_shopping_service.entity;

import mx.com.ismaeloe.product.entity.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tbl_invoce_items")
public class InvoiceItem implements Serializable {

    private static final long serialVersionUID = 3683994209021067247L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;

	private Double  price;

    @Column(name = "product_id")
    private Long productId;

    //@Transient
    //private Double subTotal;

    @Transient
    private Product product;

    /*TODO Lombok*/
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	/* it's calculated
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}*/

	public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    
    public InvoiceItem(){
        this.quantity=(double) 0;
        this.price=(double) 0;
    }
}
