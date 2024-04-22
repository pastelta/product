package ru.course.taskfive.service;

import org.springframework.http.ResponseEntity;
import ru.course.taskfive.model.Product;

public interface ProductServiceable {
    ResponseEntity<Object> saveProduct (Product product);
}
