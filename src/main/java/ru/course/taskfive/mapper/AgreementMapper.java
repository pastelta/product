package ru.course.taskfive.mapper;

import ru.course.taskfive.entity.Agreement;
import ru.course.taskfive.entity.TppProduct;
import ru.course.taskfive.model.Arrangement;

import java.sql.Timestamp;
import java.util.Objects;

public class AgreementMapper {
    public static Agreement mapToAgreement(TppProduct tppProduct, Arrangement arrangement) {
        Agreement agreement = new Agreement();

        agreement.setProduct_id(tppProduct);
        agreement.setGeneral_agreement_id(arrangement.getGeneralAgreementId());
        agreement.setSupplementary_agreement_id(arrangement.getSupplementaryAgreementId());
        agreement.setArrangement_type(arrangement.getArrangementType());
        agreement.setSheduler_job_id(arrangement.getShedulerJobId());
        agreement.setNumber(arrangement.getNumber());
        agreement.setOpening_date(Timestamp.valueOf(arrangement.getOpeningDate().toString() + " 00:00:00"));
        if(!Objects.isNull(arrangement.getClosingDate()))
            agreement.setClosing_date(Timestamp.valueOf(arrangement.getClosingDate().toString() + " 00:00:00"));
        if(!Objects.isNull(arrangement.getCancelDate()))
        agreement.setCancel_date(Timestamp.valueOf(arrangement.getCancelDate().toString() + " 00:00:00"));
        agreement.setValidity_duration(arrangement.getValidityDuration());
        agreement.setCancellation_reason(arrangement.getCancellationReason());
        agreement.setStatus(arrangement.getStatus());
        if(!Objects.isNull(arrangement.getInterestCalculationDate()))
            agreement.setInterest_calculation_date(Timestamp.valueOf(arrangement.getInterestCalculationDate().toString() + " 00:00:00"));
        agreement.setInterest_rate(arrangement.getInterestRate());
        agreement.setCoefficient(arrangement.getCoefficient());
        agreement.setCoefficient_action(arrangement.getCoefficientAction());
        agreement.setMaximal_interest_rate(arrangement.getMinimumInterestRate());
        agreement.setMinimum_interest_rate_coefficient(arrangement.getMinimumInterestRateCoefficient());
        agreement.setMinimum_interest_rate_coefficient_action(arrangement.getMinimumInterestRateCoefficientAction());
        agreement.setMaximal_interest_rate(arrangement.getMaximalnterestRate());
        agreement.setMaximal_interest_rate_coefficient(arrangement.getMaximalnterestRateCoefficient());
        agreement.setMaximal_interest_rate_coefficient_action(arrangement.getMaximalnterestRateCoefficientAction());

        return agreement;
    }
}
