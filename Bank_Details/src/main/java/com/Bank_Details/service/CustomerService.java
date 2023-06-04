package com.Bank_Details.service;

import com.Bank_Details.entities.Customer;
import com.Bank_Details.payload.BankDTO;
import com.Bank_Details.payload.CustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(long bankId,CustomerDTO customerDTO);

    List<CustomerDTO> getCustomerByBankId(long bankId);

    CustomerDTO getCustomerById(long bankId, long customerId);

    CustomerDTO updateCustomer(long bankId, long customerId, CustomerDTO customerDTO);

    void deleteCustomer(long bankId, long customerId);
}
