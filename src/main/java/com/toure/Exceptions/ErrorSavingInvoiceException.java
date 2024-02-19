package com.toure.Exceptions;

public class ErrorSavingInvoiceException extends  RuntimeException{
    public ErrorSavingInvoiceException(){
        super("Failed to Add Invoice ");
    }


}
