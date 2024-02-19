package com.toure.RestClient;

import com.toure.Model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface InvoiceRestClient {
    @GetMapping(path = "customer/{customerId}")
    Customer getCustomer(@PathVariable Long id);
}
