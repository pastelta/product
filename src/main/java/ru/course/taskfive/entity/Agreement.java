package ru.course.taskfive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private TppProduct product_id;
    private String general_agreement_id;
    private String supplementary_agreement_id;
    private String arrangement_type;
    private Integer sheduler_job_id;
    private String number;
    private Timestamp opening_date;
    private Timestamp closing_date;
    private Timestamp cancel_date;
    private Integer validity_duration;
    private String cancellation_reason;
    private String status;
    private Timestamp interest_calculation_date;
    private BigDecimal interest_rate;
    private BigDecimal coefficient;
    private String coefficient_action;
    private BigDecimal minimum_interest_rate;
    private BigDecimal minimum_interest_rate_coefficient;
    private String minimum_interest_rate_coefficient_action;
    private BigDecimal maximal_interest_rate;
    private BigDecimal maximal_interest_rate_coefficient;
    private String maximal_interest_rate_coefficient_action;
    public Agreement() {
    }
}
