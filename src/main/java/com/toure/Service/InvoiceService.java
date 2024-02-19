package com.toure.Service;

import com.toure.Dtos.InvoiceRequestDto;
import com.toure.Dtos.InvoiceResponseDto;
import com.toure.Exceptions.ErrorSavingInvoiceException;
import com.toure.Exceptions.InvoiceNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDto addInvoice(InvoiceRequestDto invoiceRequestDto) throws ErrorSavingInvoiceException;
    InvoiceResponseDto updateInvoice(Long id,InvoiceRequestDto invoiceRequestDto) throws InvoiceNotFoundException;
    InvoiceResponseDto getInvoice(Long id) throws InvoiceNotFoundException;
    List<InvoiceResponseDto> getAllInvoice() throws InvoiceNotFoundException;
    void deleteInvoice(Long id) throws InvoiceNotFoundException;
}
