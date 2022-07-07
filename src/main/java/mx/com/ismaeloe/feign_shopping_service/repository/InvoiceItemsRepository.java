package mx.com.ismaeloe.feign_shopping_service.repository;

import mx.com.ismaeloe.feign_shopping_service.entity.InvoiceItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
	
}
