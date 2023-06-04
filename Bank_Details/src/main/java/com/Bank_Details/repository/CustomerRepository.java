package com.Bank_Details.repository;

import com.Bank_Details.entities.Customer;
import com.Bank_Details.payload.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    List<Customer> findByBankId(long bankId);
}
