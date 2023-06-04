package com.Bank_Details.controller;

import com.Bank_Details.entities.Customer;
import com.Bank_Details.payload.BankDTO;
import com.Bank_Details.payload.CustomerDTO;
import com.Bank_Details.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //http://localhost:8383/api/banks/1/customer


    // retrieve bank entity by id


    @PostMapping("/banks/{bankId}/customer")
    public ResponseEntity<Object> createCustomer(@Valid @PathVariable(name = "bankId")long bankId, @RequestBody CustomerDTO customerDTO, BindingResult result){

        if(result.hasErrors()){

            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
       CustomerDTO dto =  customerService.createCustomer(bankId,customerDTO);
       return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8383/api/banks/1/customer


    // retrieve customer by bankId

    @GetMapping("/banks/{bankId}/customer")
    public List<CustomerDTO>getCustomerByBankId(@PathVariable("bankId")long bankId){
       return customerService.getCustomerByBankId(bankId);

    }

    //http://localhost:8383/api/banks/3/customer/1
    @GetMapping("/banks/{bankId}/customer/{id}")
    public ResponseEntity<CustomerDTO>getCustomerById(@PathVariable("bankId")long bankId,@PathVariable("id")long CustomerId){
     CustomerDTO customerDTO = customerService.getCustomerById(bankId,CustomerId);
     return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }
    @PutMapping("/banks/{bankId}/customer/{id}")
    public ResponseEntity<CustomerDTO>updateCustomer(@PathVariable("bankId")long bankId,@PathVariable("id")long customerId,@RequestBody CustomerDTO customerDTO){
       CustomerDTO newCustomer = customerService.updateCustomer(bankId,customerId,customerDTO);
       return new ResponseEntity<>(newCustomer,HttpStatus.OK);
    }
    @DeleteMapping("/banks/{bankId}/customer/{id}")
    public ResponseEntity<String>deleteCustomer(@PathVariable("bankId")long bankId,@PathVariable("id")long customerId){
        customerService.deleteCustomer(bankId,customerId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
