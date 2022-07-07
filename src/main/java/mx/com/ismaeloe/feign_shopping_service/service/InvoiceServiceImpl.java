package mx.com.ismaeloe.feign_shopping_service.service;

import mx.com.ismaeloe.customer.entity.Customer;
import mx.com.ismaeloe.product.entity.Product;

import mx.com.ismaeloe.feign_shopping_service.entity.Invoice;

import mx.com.ismaeloe.feign_shopping_service.feignclient.CustomerClient;
import mx.com.ismaeloe.feign_shopping_service.feignclient.ProductClient;

import mx.com.ismaeloe.feign_shopping_service.entity.InvoiceItem;

import mx.com.ismaeloe.feign_shopping_service.repository.InvoiceItemsRepository;
import mx.com.ismaeloe.feign_shopping_service.repository.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;
    
    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }

    @Override
    public Invoice findInvoiceById(Long id) {

    	log.debug("findInvoiceById() ={}", id);
    	
        Invoice invoice= invoiceRepository.findById(id).orElse(null);

        if (null != invoice ){

        	Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
        	
            List<InvoiceItem> listItem=invoice.getItems().stream().map(invoiceItem -> {

            	//TODO enable Feign
            	Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
            	invoiceItem.setProduct(product);
            	
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice ;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );

        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);

        invoiceDB.getItems().forEach( invoiceItem -> {
        	//TODO enable Feign
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });

        return invoiceDB;
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = findInvoiceById(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = findInvoiceById(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

}
