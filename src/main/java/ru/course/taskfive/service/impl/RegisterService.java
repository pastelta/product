package ru.course.taskfive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.course.taskfive.entity.*;
import ru.course.taskfive.enums.StateAccount;
import ru.course.taskfive.mapper.RegisterMapper;
import ru.course.taskfive.model.Register;
import ru.course.taskfive.model.RegisterResponse;
import ru.course.taskfive.repository.AccountPoolRepositoryable;
import ru.course.taskfive.repository.AccountRepositoryable;
import ru.course.taskfive.repository.TppRefRegisterTypeRepositoryable;
import ru.course.taskfive.repository.TppRegisterRepositoryable;
import ru.course.taskfive.service.RegisterServiceable;

@Service
@AllArgsConstructor
public class RegisterService implements RegisterServiceable {
    private final TppRegisterRepositoryable REGISTER_REPOSITORY;
    private final TppRefRegisterTypeRepositoryable REGISTER_TYPE_REPOSITORY;
    private final AccountPoolRepositoryable ACCOUNT_POOL_REPOSITORY;
    private final AccountRepositoryable ACCOUNT_REPO;

    @Override
    @Transactional
    public ResponseEntity<Object> saveRegister(Register register) {

        //Шаг 2 ПР
        if (REGISTER_REPOSITORY.findAll().stream().filter(i -> i.getType().equals(register.getRegistryTypeCode()))
                .filter(i -> i.getProduct_id().equals(register.getInstanceId()))
                .mapToInt(i -> i.getProduct_id()).count() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Параметр тип регистра registryTypeCode: \""
                    + register.getRegistryTypeCode() + "\" для ЭП с id: \""
                    + register.getInstanceId() + "\" уже существует");
        }

        //Шаг 3 ПР
        if(!REGISTER_TYPE_REPOSITORY.findAll().stream().filter(i->i.getValue().equals(register.getRegistryTypeCode())).findFirst().isPresent()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Код Продукта \""
                    + register.getRegistryTypeCode() + "\" не найден в Каталоге продуктов <prod.tpp_ref_product_register_type> для данного типа Регистра");
        }

        //Шаг 4 ПР
        TppProductRegister tppProductRegister = RegisterMapper.mapToTppProductRegister(register);
        tppProductRegister.setState(StateAccount.OPENED.toString());

        AccountPool accountPool = ACCOUNT_POOL_REPOSITORY.getAcc(register.getBranchCode(), register.getCurrencyCode());
        tppProductRegister.setAccount(accountPool.getId());
        tppProductRegister.setAccount_number(ACCOUNT_REPO.getAccountNumber(accountPool.getId()));
        tppProductRegister.setType(register.getRegistryTypeCode());

        TppProductRegister res = REGISTER_REPOSITORY.save(tppProductRegister);
        RegisterResponse registerResponse = new RegisterResponse("200", "OK", res.getId());

        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
}
