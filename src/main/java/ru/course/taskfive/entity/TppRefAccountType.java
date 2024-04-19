package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name="tpp_ref_account_type")
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internal_id;
    private String value;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_type")
    private List<TppRefProductRegisterType> tppRefProductRegisterTypeList = new ArrayList<>();
    public TppRefAccountType() {
    }
}
