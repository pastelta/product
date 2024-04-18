package ru.course.taskfive.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.course.taskfive.entity.TppProductRegister;
import ru.course.taskfive.mapper.RegisterMapper;
import ru.course.taskfive.model.Register;
import ru.course.taskfive.repository.TppRegisterRepositoryable;
import ru.course.taskfive.service.RegisterServiceable;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class RegisterService implements RegisterServiceable {
    private final TppRegisterRepositoryable REGISTER_REPOSITORY;
    @Override
    public void saveRegister(Register register) {
        List<TppProductRegister> tppProductRegisterList  = REGISTER_REPOSITORY.findAll();

        register.getInstanceId();
        register.getRegistryTypeCode();

        TppProductRegister tppProductRegister = RegisterMapper.mapToTppProductRegister(register);
        REGISTER_REPOSITORY.save(tppProductRegister);
    }
}
