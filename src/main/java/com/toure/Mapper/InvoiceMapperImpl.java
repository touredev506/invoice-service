package com.toure.Mapper;

import com.toure.Dtos.InvoiceRequestDto;
import com.toure.Dtos.InvoiceResponseDto;
import com.toure.Model.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceMapperImpl implements InvoiceMapper {
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public InvoiceResponseDto toDto(Invoice invoice) {
        return modelMapper.map(invoice,InvoiceResponseDto.class);
    }

    @Override
    public List<InvoiceResponseDto> toListDto(List<Invoice> invoices) {
        return invoices.stream().map(in->modelMapper.map(in,InvoiceResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public Invoice toInvoice(InvoiceRequestDto invoiceRequestDto) {
        return modelMapper.map(invoiceRequestDto,Invoice.class);
    }
}
