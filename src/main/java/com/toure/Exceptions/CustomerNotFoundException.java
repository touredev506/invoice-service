package com.toure.Exceptions;

public class CustomerNotFoundException extends  RuntimeException{
    public CustomerNotFoundException() {
        super("Customer not Found");
    }
}
