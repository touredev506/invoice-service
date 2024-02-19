package com.toure.Mapper;

import com.toure.Dtos.InvoiceRequestDto;
import com.toure.Dtos.InvoiceResponseDto;
import com.toure.Model.Invoice;

import java.util.List;

public interface InvoiceMapper {
    Invoice toInvoice(InvoiceRequestDto invoiceRequestDto) ;
    InvoiceResponseDto toDto(Invoice invoice);
    List<InvoiceResponseDto>toListDto(List<Invoice> invoices);

    }











