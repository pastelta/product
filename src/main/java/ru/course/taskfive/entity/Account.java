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
    @ManyToOne(optional = false, cascade = CascadeType.ALL, targetEntity = AccountPool.class)
    @JoinColumn(name = "account_pool_id")
    private Integer account_poll_id;
    private String account_number;
    public Account() {
    }
}
