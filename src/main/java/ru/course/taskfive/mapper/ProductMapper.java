package ru.course.taskfive.mapper;

import ru.course.taskfive.entity.Agreement;
import ru.course.taskfive.entity.TppProduct;
import ru.course.taskfive.enums.StateAccount;
import ru.course.taskfive.model.Arrangement;
import ru.course.taskfive.model.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static TppProduct mapToTppProduct(Product product) {
        List<Agreement> agreementList = new ArrayList<>();
        List<Arrangement> arrangementList = product.getInstanceArrangement();
        TppProduct tppProduct = new TppProduct();
        tppProduct.setProduct_code_id(Integer.parseInt(product.getProductCode()));
        tppProduct.setClient_id(Integer.parseInt(product.getMdmCode()));
        tppProduct.setType(product.getProductType());
        tppProduct.setNumber(product.getContractNumber());
        tppProduct.setPriority(product.getPriority());
        tppProduct.setDate_of_conclusion(Timestamp.valueOf(product.getContractDate().toString() + " 00:00:00"));
        tppProduct.setStart_date_time(Timestamp.valueOf(product.getContractDate().toString() + " 00:00:00"));
        tppProduct.setDays(0);
        tppProduct.setPenalty_rate(product.getInterestRatePenalty());
        tppProduct.setNso(product.getMinimalBalance());
        tppProduct.setThreshold_amount(product.getThresholdAmount());
        tppProduct.setTax_rate(product.getTaxPercentageRate());
        tppProduct.setState(StateAccount.открыт.toString());

        for (Arrangement a :
                arrangementList) {
            Agreement agreement = AgreementMapper.mapToAgreement(tppProduct, a);
            agreementList.add(agreement);
        }

        tppProduct.setAgreementList(agreementList);

        return tppProduct;
    }
}
