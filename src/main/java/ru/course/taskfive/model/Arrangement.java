package ru.course.taskfive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arrangement implements Serializable {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotBlank(message = "Значение обязательного параметра number отсутствует")
    private String number;
    @NotNull(message = "Значение обязательного параметра openingDate отсутствует")
    private LocalDate openingDate;
    private LocalDate closingDate;
    private LocalDate cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    private LocalDate interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    private String coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private BigDecimal maximalnterestRate;
    private BigDecimal maximalnterestRateCoefficient;
    private String maximalnterestRateCoefficientAction;
}
