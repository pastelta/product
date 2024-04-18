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
    private Integer id;
    private String value;
    private String register_type_name;
    @Column
    private String product_class_code;
    private Timestamp register_type_start_date;
    private Timestamp register_type_end_date;
    @Column
    private String account_type;
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER, targetEntity = TppProductRegister.class)
    private List<TppProductRegister> tppProductRegisterList = new ArrayList<>();

    public TppRefProductRegisterType() {
    }
}
