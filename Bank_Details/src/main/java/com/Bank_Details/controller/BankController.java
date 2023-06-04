package com.Bank_Details.controller;

import com.Bank_Details.payload.BankDTO;
import com.Bank_Details.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    //get all bank rest api
    //http://localhost:8080/api/banks

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createBank(@Valid @RequestBody BankDTO bankDTO, BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BankDTO createdBank = bankService.createBank(bankDTO);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }


    @GetMapping
    public List<BankDTO>getAllBank(){
       return bankService.getAllBank();
    }

    //get By id
    //http://localhost:8080/api/banks/1
    @GetMapping("/{id}")
    public ResponseEntity<BankDTO>getBankById(@PathVariable("id")long id){
        BankDTO dto = bankService.getBankById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
   public ResponseEntity<BankDTO> updateBank(@RequestBody BankDTO bankDTO,@PathVariable ("id")long id){
       BankDTO newBank = bankService.updateBank(bankDTO,id);
       return new ResponseEntity<>(newBank,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteBank(@PathVariable("id")long id ){
       bankService.deletBankById(id);
       return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
   }
}