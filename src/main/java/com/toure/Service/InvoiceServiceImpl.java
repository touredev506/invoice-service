package com.toure.Service;

import com.toure.Dtos.InvoiceRequestDto;
import com.toure.Dtos.InvoiceResponseDto;
import com.toure.Exceptions.CustomerNotFoundException;
import com.toure.Exceptions.ErrorSavingInvoiceException;
import com.toure.Exceptions.InvoiceNotFoundException;
import com.toure.Mapper.InvoiceMapper;
import com.toure.Model.Customer;
import com.toure.Model.Invoice;
import com.toure.Repository.InvoiceRepository;
import com.toure.RestClient.InvoiceRestClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Service
@Transactional
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRestClient invoiceRestClient;
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;

    @Override
    public InvoiceResponseDto addInvoice(InvoiceRequestDto invoiceRequestDto) throws ErrorSavingInvoiceException {
        // Check that the InvoiceRequestDto object is not null
        if (invoiceRequestDto == null) {
            log.info("Attempt to add an invoice with a null InvoiceRequestDto object");
            throw new ErrorSavingInvoiceException();
        }

        // Check that the attributes of the InvoiceRequestDto object contain at least 4 characters
        if (invoiceRequestDto.getLabel().length() < 4) {
            log.info("Attempt to add an invoice with attributes that are too short");
            throw new ErrorSavingInvoiceException();
        }

        // Check if the customer exists
        Customer customer = invoiceRestClient.getCustomer(invoiceRequestDto.getCustomerId());
        if (customer == null) {
            log.info("Attempt to add an invoice for a non-existent customer");
            throw new ErrorSavingInvoiceException();
        }

        // Saving of Invoice
        Invoice invoice = invoiceMapper.toInvoice(invoiceRequestDto);
        invoice.setCreatedAt(LocalDate.now());
        invoice.setTotal_Price(invoice.getQuantity() * invoice.getUnit_Price());

        Invoice invoiceSaved = invoiceRepository.save(invoice);

        // Create and return the response

        return invoiceMapper.toDto(invoiceSaved);
    }

    @Override
    public InvoiceResponseDto updateInvoice(Long id, InvoiceRequestDto invoiceRequestDto) throws InvoiceNotFoundException {
        // Check if the Invoice exists
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Invoice not found for id: " + id);
                    return new InvoiceNotFoundException();
                });

        // Check that the InvoiceRequestDto object is not null
        if (invoiceRequestDto == null) {
            log.info("Attempt to update an invoice with a null InvoiceRequestDto object");
            throw new ErrorSavingInvoiceException();
        }

        // Check that the attributes of the InvoiceRequestDto object contain at least 4 characters
        if (invoiceRequestDto.getLabel().length() < 4) {
            log.info("Attempt to update an invoice with attributes that are too short");
            throw new ErrorSavingInvoiceException();
        }

        // Check if the customer exists
        Customer customer = invoiceRestClient.getCustomer(invoiceRequestDto.getCustomerId());
        if (customer == null) {
            log.info("Attempt to update an invoice for a non-existent customer");
            throw new ErrorSavingInvoiceException();
        }

        // Update Invoice attributes
        invoice.setLabel(invoiceRequestDto.getLabel());
        invoice.setQuantity(invoiceRequestDto.getQuantity());
        invoice.setUnit_Price(invoiceRequestDto.getUnit_Price());

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        // Return the updated Invoice response
        log.info("Invoice updated successfully with id: " + id);
        return invoiceMapper.toDto(updatedInvoice);
    }

    @Override
    public InvoiceResponseDto getInvoice(Long id) throws InvoiceNotFoundException {
        // Check if the Invoice exists
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Invoice not found for id: " + id);
                    return new InvoiceNotFoundException();
                });

        // Check if the customer exists
        Customer customer = invoiceRestClient.getCustomer(invoice.getCustomerId());
        if (customer == null) {
            log.info("Invoice belongs to a non-existent customer");
            throw new CustomerNotFoundException();
        }
        invoice.setCustomer(customer);
        // Map Invoice to InvoiceResponseDto
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public List<InvoiceResponseDto> getAllInvoice() throws InvoiceNotFoundException {
        List<Invoice> invoices = invoiceRepository.findAll();

// Check if the customer exists for each invoice
        for (Invoice invoice : invoices) {
            Customer customer = invoiceRestClient.getCustomer(invoice.getCustomerId());
            if (customer == null) {
                log.info("Invoice with ID " + invoice.getId() + " belongs to a non-existent customer");
                throw new CustomerNotFoundException();

            }
            invoice.setCustomer(customer);
        }
        return invoiceMapper.toListDto(invoices);
    }

    @Override
    public void deleteInvoice(Long id) throws InvoiceNotFoundException {
        // Check if the invoice exists
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Invoice not found for id: " + id);
                    return new InvoiceNotFoundException();
                });

        // Check if the customer exists
        Customer customer = invoiceRestClient.getCustomer(invoice.getCustomerId());
        if (customer == null) {
            log.info("Invoice belongs to a non-existent customer");
            throw new CustomerNotFoundException();
        }
        // Delete the invoice
        invoiceRepository.delete(invoice);
    }

}

