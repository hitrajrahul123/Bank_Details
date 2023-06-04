package com.Bank_Details.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PassBook_Details")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bankName;
    private long accountNumber;
    private String customerName;
    private String accountType;
    private String address;
    private long customerNumber;
    private String branch;
    private String ifscCode;
    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL,orphanRemoval = true)

    private Set<Customer> customers=new HashSet<>();
}