package com.votybe.bankaccount.transactions;

import com.votybe.bankaccount.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_account_id", nullable = false)
    private Integer fromAccountId;

    @Column(name = "to_account_id", nullable = false)
    private Integer toAccountId;

    @Column(nullable = false)
    private double amount;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
