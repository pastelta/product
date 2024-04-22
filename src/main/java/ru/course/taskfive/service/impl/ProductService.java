package ru.course.taskfive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.course.taskfive.entity.Agreement;
import ru.course.taskfive.entity.TppProduct;
import ru.course.taskfive.entity.TppProductRegister;
import ru.course.taskfive.mapper.AgreementMapper;
import ru.course.taskfive.mapper.ProductMapper;
import ru.course.taskfive.model.Arrangement;
import ru.course.taskfive.model.Product;
import ru.course.taskfive.model.ProductResponse;
import ru.course.taskfive.repository.AgreementRepositoryable;
import ru.course.taskfive.repository.TppProductRepositoryable;
import ru.course.taskfive.repository.TppRegisterRepositoryable;
import ru.course.taskfive.service.ProductServiceable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductService implements ProductServiceable {
    private final TppProductRepositoryable PRODUCT_REPOSITORY;
    private final AgreementRepositoryable AGREEMENT_REPOSITORY;
    private final TppRegisterRepositoryable REGISTER_REPOSITORY;

    @Override
    @Transactional
    public ResponseEntity<Object> saveProduct(Product product) {
        List<TppProduct> tppProductList = PRODUCT_REPOSITORY.findAll();
        TppProduct p = ProductMapper.mapToTppProduct(product);

        List<Integer> agreementIdList = new ArrayList<>();
        ProductResponse resultRs = new ProductResponse();
        TppProductRegister tppProductRegister = new TppProductRegister();

        if (tppProductList.stream().filter(i -> i.getId().equals(product.getInstanceId())).findFirst().isPresent()) {
            int index = IntStream.range(0, tppProductList.size()).filter(i -> tppProductList.get(i).getId().equals(product.getInstanceId())).findFirst().orElse(-1);
            TppProduct pEx = tppProductList.get(index);

            for (Arrangement a : product.getInstanceArrangement()) {
                if (AGREEMENT_REPOSITORY.findAll().stream().filter(i -> i.getNumber().equals(a.getNumber())).findFirst().isPresent()) {
                    return new ResponseEntity<Object>("Параметр доп. соглашения number: \"" + a.getNumber() + "\" уже существует", HttpStatus.BAD_REQUEST);
                }
                Agreement agreement = AgreementMapper.mapToAgreement(pEx, a);
                Agreement agr = AGREEMENT_REPOSITORY.save(agreement); //если instanceId есть, то создали ДС к ЭПH
                agreementIdList.add(agr.getId());
            }

            tppProductRegister.setProduct_id(product.getInstanceId());
            tppProductRegister.setType(product.getRegisterType());
            TppProductRegister tppProductRegisterResult = REGISTER_REPOSITORY.save(tppProductRegister); //создали запись

            resultRs = new ProductResponse("200", "OK", product.getInstanceId(), agreementIdList, tppProductRegisterResult.getId());

        } else {
            if (product.getInstanceId() != null) {
                return new ResponseEntity<Object>("Экземпляр продукта с параметром instanceId: \"" + product.getInstanceId() + "\" не найден\"", HttpStatus.NOT_FOUND);
            }
            if (tppProductList.stream().filter(i -> i.getNumber().equals(product.getContractNumber())).findFirst().isPresent()) {
                return new ResponseEntity<Object>("Параметр договора contractNumber: \"" + product.getContractNumber() + "\" уже существует", HttpStatus.BAD_REQUEST);
            }
            for (Arrangement a : product.getInstanceArrangement()) {
                if (AGREEMENT_REPOSITORY.findAll().stream().filter(i -> i.getNumber().equals(a.getNumber())).findFirst().isPresent()) {
                    return new ResponseEntity<Object>("Параметр доп. соглашения number: \"" + a.getNumber() + "\" уже существует", HttpStatus.BAD_REQUEST);
                }

                Agreement agreement = AgreementMapper.mapToAgreement(p, a);
                Agreement agr = AGREEMENT_REPOSITORY.save(agreement); //создали ДС к ЭПH
                agreementIdList.add(agr.getId());
            }

            TppProduct res = PRODUCT_REPOSITORY.save(p);

            //Шаг 1.5 ЭП.
            tppProductRegister.setProduct_id(res.getId());
            tppProductRegister.setType(product.getRegisterType());
            TppProductRegister tppProductRegisterResult = REGISTER_REPOSITORY.save(tppProductRegister);

            resultRs = new ProductResponse("200", "OK", res.getId(), agreementIdList, tppProductRegisterResult.getId());
        }
        return new ResponseEntity<>(resultRs, HttpStatus.OK);
    }
}