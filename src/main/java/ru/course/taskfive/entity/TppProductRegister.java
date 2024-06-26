package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product_id;
    @Column(name = "type")
    private String type;
    private Integer account;
    private String currency_code;
    private String state;
    private String account_number;

    public TppProductRegister() {
    }
}
