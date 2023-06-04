package com.Bank_Details.service.impl;

import com.Bank_Details.entities.Bank;
import com.Bank_Details.exception.ResourceNotFoundException;
import com.Bank_Details.payload.BankDTO;
import com.Bank_Details.repository.BankRepository;
import com.Bank_Details.service.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;
    private ModelMapper mapper ;
    public BankServiceImpl(BankRepository bankRepository,ModelMapper mapper) {
        this.bankRepository = bankRepository;
        this.mapper = mapper;
    }

    @Override
    public BankDTO createBank(BankDTO bankDTO) {
     Bank bank=mapToEntity(bankDTO);
        Bank newBank = bankRepository.save(bank);
        BankDTO dto=mapToDTO(newBank);
        return dto;
    }

    @Override
    public List<BankDTO> getAllBank() {
        List<Bank> banks = bankRepository.findAll();

        return banks.stream().map(bank -> mapToDTO(bank)).collect(Collectors.toList());
    }

    @Override
    public BankDTO getBankById(long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("bank","id",id));
        return mapToDTO(bank) ;
    }

    @Override
    public BankDTO updateBank(BankDTO bankDTO, long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bank", "id", id));
        bank.setBankName(bankDTO.getBankName());
        bank.setCustomerName(bankDTO.getCustomerName());
        bank.setCustomerNumber(bankDTO.getCustomerNumber());
        bank.setBranch(bankDTO.getBranch());
        bank.setAccountType(bankDTO.getAccountType());
        bank.setIfscCode(bankDTO.getIfscCode());
        bank.setAddress(bankDTO.getAddress());
        Bank newBANK = bankRepository.save(bank);
        return mapToDTO(newBANK);
    }

    @Override
    public void deletBankById(long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bank", "id", id));
       bankRepository.deleteById(id);

    }



    private BankDTO mapToDTO(Bank bank){
        BankDTO dto = mapper.map(bank, BankDTO.class);

        return dto;
    }
    private Bank mapToEntity(BankDTO bankDTO){
        Bank map = mapper.map(bankDTO, Bank.class);
        return map;
    }
}
