package ru.course.taskfive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer instanceId;
    @NotBlank(message = "Значение обязательного параметра productType отсутствует")
    private String productType;
    @NotBlank(message = "Значение обязательного параметра productCode отсутствует")
    private String productCode;
    @NotBlank(message = "Значение обязательного параметра registerType отсутствует")
    private String registerType;
    @NotBlank(message = "Значение обязательного параметра mdmCode отсутствует")
    private String mdmCode;
    @NotBlank(message = "Значение обязательного параметра contractNumber отсутствует")
    private String contractNumber;
    @NotNull(message = "Значение обязательного параметра contractDate отсутствует")
    private Date contractDate;
    @NotNull(message = "Значение обязательного параметра priority отсутствует")
    private Integer priority;
    private BigDecimal interestRatePenalty;
    private BigDecimal minimalBalance;
    private BigDecimal thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private BigDecimal taxPercentageRate;
    private BigDecimal technicalOverdraftLimitAmount;
    @NotNull(message = "Значение обязательного параметра contractId отсутствует")
    private Integer contractId;
    @NotNull(message = "Значение обязательного параметра branchCode отсутствует")
    private String branchCode;
    @NotBlank(message = "Значение обязательного параметра isoCurrencyCode отсутствует")
    private String isoCurrencyCode;
    @NotBlank(message = "Значение обязательного параметра urgencyCode отсутствует")
    private String urgencyCode;
    private Integer referenceCode;
    @NotNull(message = "Обязательный параметр instanceArrangement отсутствует")
    private List<Arrangement> instanceArrangement;
}
