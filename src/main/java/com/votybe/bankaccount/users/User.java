package com.votybe.bankaccount.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.votybe.bankaccount.transactions.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Transaction> transactionHistory;
}
