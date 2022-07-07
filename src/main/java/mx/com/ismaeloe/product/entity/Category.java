package mx.com.ismaeloe.product.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categories")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
   
    /*
     * Default contructor created with @NoArgsConstructor
	    public Category() {
			super();
		}
	
		//Created with @AllArgsConstructor
		public Category(Long id) {
			super();
		}
	
		public Category(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	*/
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    
}
