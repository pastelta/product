package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="account_pool")
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String branch_code;
    private String currency_code;
    private String mdm_code;
    private String priority_code;
    private String registry_type_code;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<Account> accountList;
    public AccountPool() {
    }
}
