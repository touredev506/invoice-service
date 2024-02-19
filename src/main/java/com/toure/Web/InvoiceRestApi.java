package com.toure.Web;

import com.toure.Dtos.InvoiceRequestDto;
import com.toure.Dtos.InvoiceResponseDto;
import com.toure.Exceptions.CustomerNotFoundException;
import com.toure.Exceptions.ErrorSavingInvoiceException;
import com.toure.Exceptions.InvoiceNotFoundException;
import com.toure.Service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api")
@AllArgsConstructor
public class InvoiceRestApi {
    private final InvoiceService invoiceService;

    @PostMapping("/add")
    public ResponseEntity<InvoiceResponseDto> addInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        try {
            InvoiceResponseDto response = invoiceService.addInvoice(invoiceRequestDto);
            return ResponseEntity.ok(response);
        } catch (ErrorSavingInvoiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<InvoiceResponseDto> updateInvoice(
            @PathVariable("id") Long id,
            @RequestBody InvoiceRequestDto invoiceRequestDto
    ) {
        try {
            InvoiceResponseDto response = invoiceService.updateInvoice(id, invoiceRequestDto);
            return ResponseEntity.ok(response);
        } catch (InvoiceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorSavingInvoiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("invoices/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoice(@PathVariable("id") Long id) {
        try {
            InvoiceResponseDto response = invoiceService.getInvoice(id);
            return ResponseEntity.ok(response);
        } catch (InvoiceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("invoices")
    public ResponseEntity<List<InvoiceResponseDto>> getAllInvoice() {
        try {
            List<InvoiceResponseDto> response = invoiceService.getAllInvoice();
            return ResponseEntity.ok(response);
        } catch (InvoiceNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("id") Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build();
        } catch (InvoiceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
