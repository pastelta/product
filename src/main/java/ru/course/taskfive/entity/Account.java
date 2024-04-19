package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_pool_id")
    private AccountPool account_pool_id;
    private String account_number;
    public Account() {
    }
}
