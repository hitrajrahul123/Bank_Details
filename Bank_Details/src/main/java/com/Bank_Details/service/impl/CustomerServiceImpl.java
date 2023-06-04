package com.Bank_Details.service.impl;

import com.Bank_Details.entities.Bank;
import com.Bank_Details.entities.Customer;
import com.Bank_Details.exception.BankApiException;
import com.Bank_Details.exception.ResourceNotFoundException;
import com.Bank_Details.payload.BankDTO;
import com.Bank_Details.payload.CustomerDTO;
import com.Bank_Details.repository.BankRepository;
import com.Bank_Details.repository.CustomerRepository;
import com.Bank_Details.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private BankRepository bankRepository;

    private ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,BankRepository bankRepository,ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.bankRepository = bankRepository;
        this.mapper = mapper;
    }


    @Override
    public CustomerDTO createCustomer(long bankId, CustomerDTO customerDTO) {

        Customer customer= mapToEntity(customerDTO);
        Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new ResourceNotFoundException("bank", "id", bankId));

        customer.setBank(bank);

        Customer newcustomer = customerRepository.save(customer);

        CustomerDTO dto = mapToDto(newcustomer);

        return dto;
    }

  @Override
    public List<CustomerDTO> getCustomerByBankId(long bankId) {
        List<Customer> customers = customerRepository.findByBankId(bankId);
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
   }

    @Override
    public CustomerDTO getCustomerById(long bankId, long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "id", customerId));
        Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new ResourceNotFoundException("bank", "id", bankId));
        if(customer.getBank().getId()!=bank.getId()){
            throw new BankApiException(HttpStatus.BAD_REQUEST,"Customer doest not belong to bank");
        }



        return mapToDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(long bankId, long customerId, CustomerDTO customerDTO) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "id", customerId));
        Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new ResourceNotFoundException("bank", "id", bankId));
        if(customer.getBank().getId()!=bank.getId()){
            throw new BankApiException(HttpStatus.BAD_REQUEST,"Customer doest not belong to bank");
        }
        customer.setId(customerId);
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerType(customerDTO.getCustomerType());
        customer.setAccountType(customerDTO.getAccountType());
        customer.setCustomerCategory(customerDTO.getCustomerCategory());
        Customer save = customerRepository.save(customer);
        return mapToDto(save);
    }

    @Override
    public void deleteCustomer(long bankId, long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "id", customerId));
        Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new ResourceNotFoundException("bank", "id", bankId));
        if(customer.getBank().getId()!=bank.getId()){
            throw new BankApiException(HttpStatus.BAD_REQUEST,"Customer doest not belong to bank");
        }
        customerRepository.delete(customer);
    }

    private CustomerDTO mapToDto(Customer newcustomer ){
        CustomerDTO customerDTO = mapper.map(newcustomer, CustomerDTO.class);
        return customerDTO;
    }

    private Customer mapToEntity(CustomerDTO customerDTO){
        Customer map = mapper.map(customerDTO, Customer.class);
        return map;
    }
}
