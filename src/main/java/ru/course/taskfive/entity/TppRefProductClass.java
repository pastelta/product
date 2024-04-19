package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;
    @Column
    private String value;
    private String gbi_code;
    private String gbi_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_class_code")
    private List<TppRefProductRegisterType> tppRefProductRegisterTypeList = new ArrayList<>();

    public TppRefProductClass() {
    }
}
