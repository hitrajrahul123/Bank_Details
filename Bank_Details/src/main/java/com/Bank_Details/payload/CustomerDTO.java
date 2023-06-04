package com.Bank_Details.payload;

import com.Bank_Details.entities.Bank;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CustomerDTO {
    private long id;

    @NotEmpty(message = "Is mandatory")
    @Size(min =4,message = "Name should be at least 4 characters")
    private String customerName;

    @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")

    private String customerType;

    @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")
    private String accountType;

    @NotEmpty(message = "Is mandatory")
    @Size(min =2,message = "Post title should be at least 2 characters")

    private  String customerCategory;


}
