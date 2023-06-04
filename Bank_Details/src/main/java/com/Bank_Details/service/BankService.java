package com.Bank_Details.service;

import com.Bank_Details.payload.BankDTO;

import java.util.List;

public interface BankService {
    BankDTO createBank(BankDTO bankDTO);

    List<BankDTO> getAllBank();

    BankDTO getBankById(long id);

    BankDTO updateBank(BankDTO bankDTO, long id);



    void deletBankById(long id);
}
