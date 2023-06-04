package com.Bank_Details.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BankDTO {


    private long id;

    @NotEmpty(message = "Is mandatory")
    @Size(min =1,message = "Post title should be at least 1 characters")
    private String bankName;


    private long accountNumber;

    @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")
    private String customerName;

   @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")
    private String accountType;

    @NotEmpty(message = "Is mandatory")
   @Size(min =2,message = "Post title should be at least 2 characters")
    private String address;

//    @NotEmpty(message = "Is mandatory")
//    @Size(min =1,message = "Post title should be at least 1 characters")
    private long customerNumber;

    @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")
    private String branch;
   @NotEmpty(message = "Is mandatory")
   @Size(min =2,message = "Post title should be at least 2 characters")
    private String ifscCode;
}