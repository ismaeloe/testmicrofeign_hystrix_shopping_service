package mx.com.ismaeloe.feign_shopping_service.service;

import mx.com.ismaeloe.feign_shopping_service.entity.Invoice;

import java.util.List;

public interface InvoiceService {
	
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);

    public Invoice findInvoiceById(Long id);
}
