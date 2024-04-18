package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product_code_id;
    private Integer client_id;
    private String type;
    private String number;
    private Integer priority;
    private Timestamp date_of_conclusion;
    private Timestamp start_date_time;
    private Timestamp end_date_time;
    private Integer days;
    private BigDecimal penalty_rate;
    private BigDecimal nso;
    private BigDecimal threshold_amount;
    private String requisite_type;
    private String interest_rate_type;
    private BigDecimal tax_rate;
    private String reasone_close;
    private String state;
    @OneToMany(mappedBy = "product_id", fetch = FetchType.EAGER)
    private List<Agreement> agreementList;
    public TppProduct() {
    }
}
