package ru.course.taskfive.service;

import org.springframework.http.ResponseEntity;
import ru.course.taskfive.model.Register;

public interface RegisterServiceable {
    ResponseEntity<Object> saveRegister(Register register);
}
