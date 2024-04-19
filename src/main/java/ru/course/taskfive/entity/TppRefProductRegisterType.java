package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;
    private String value;
    private String register_type_name;

    @Column(name = "product_class_code")
    private String product_class_code;

    private Timestamp register_type_start_date;
    private Timestamp register_type_end_date;

    @Column(name = "account_type")
    private String account_type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private List<TppProductRegister> tppProductRegisterList;

    public TppRefProductRegisterType() {
    }
}
