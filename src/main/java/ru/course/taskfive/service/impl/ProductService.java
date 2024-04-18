package ru.course.taskfive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.course.taskfive.entity.Agreement;
import ru.course.taskfive.entity.TppProduct;
import ru.course.taskfive.mapper.AgreementMapper;
import ru.course.taskfive.mapper.ProductMapper;
import ru.course.taskfive.model.Arrangement;
import ru.course.taskfive.model.Product;
import ru.course.taskfive.repository.AgreementRepositoryable;
import ru.course.taskfive.repository.TppProductRepositoryable;
import ru.course.taskfive.service.ProductServiceable;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductService implements ProductServiceable {
    private final TppProductRepositoryable PRODUCT_REPOSITORY;
    private final AgreementRepositoryable AGREEMENT_REPOSITORY;

    @Override
    public ResponseEntity<String> saveProduct(Product product) {
        List<TppProduct> tppProductList = PRODUCT_REPOSITORY.findAll();
        List<Agreement> agreementList = AGREEMENT_REPOSITORY.findAll();

        if (tppProductList.stream().filter(i -> i.getId().equals(product.getInstanceId())).findFirst().isPresent()) {
            int index = IntStream.range(0, tppProductList.size()).filter(i -> tppProductList.get(i).getId().equals(product.getInstanceId())).findFirst().orElse(-1);
            TppProduct p = tppProductList.get(index);
            for (Arrangement a : product.getInstanceArrangement()) {
                if(agreementList.stream().filter(i->i.getNumber().equals(a.getNumber())).findFirst().isPresent()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Параметр доп. соглашения number: " + a.getNumber() + " уже существует");
                }
                Agreement agreement = AgreementMapper.mapToAgreement(p, a);
                AGREEMENT_REPOSITORY.save(agreement); //Если instanceId есть, то создали ДС к ЭПH
            }
        } else {
            if(product.getInstanceId()!=null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Экземпляр продукта с параметром instanceId: " + product.getInstanceId() + " не найден");
            }
            if (tppProductList.stream().filter(i -> i.getNumber().equals(product.getContractNumber())).findFirst().isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Параметр договора contractNumber: " + product.getContractNumber() + " уже существует");
            }
            for(Arrangement a: product.getInstanceArrangement()){
                if(agreementList.stream().filter(i->i.getNumber().equals(a.getNumber())).findFirst().isPresent()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Параметр доп. соглашения number: " + a.getNumber() + " уже существует");
                }
            }
            TppProduct p = ProductMapper.mapToTppProduct(product);
            PRODUCT_REPOSITORY.save(p);
            AGREEMENT_REPOSITORY.saveAll(p.getAgreementList());

        }
        return ResponseEntity.status(HttpStatus.OK).body(product.getContractNumber());
    }
}