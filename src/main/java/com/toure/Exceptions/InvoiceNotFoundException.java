package com.toure.Exceptions;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException() {

        super("Invoice not Found");
    }
}
