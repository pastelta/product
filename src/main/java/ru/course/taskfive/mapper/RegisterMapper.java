package ru.course.taskfive.mapper;

import ru.course.taskfive.entity.TppProductRegister;
import ru.course.taskfive.model.Register;
public class RegisterMapper {
    public static TppProductRegister mapToTppProductRegister(Register register) {
        TppProductRegister tppProductRegister = new TppProductRegister();

        tppProductRegister.setProduct_id(register.getInstanceId());
        tppProductRegister.setCurrency_code(register.getCurrencyCode());

        return tppProductRegister;
    }
}
