package ru.course.taskfive.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.course.taskfive.model.Product;
import ru.course.taskfive.model.Register;
import ru.course.taskfive.service.ProductServiceable;
import ru.course.taskfive.service.RegisterServiceable;

@Validated
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class ProductController {
    private final ProductServiceable PRODUCT_SERVICE;
    private final RegisterServiceable REGISTER_SERVICE;

    @SneakyThrows
    @PostMapping("corporate-settlement-instance/create")
    public ResponseEntity saveProduct(@Valid @RequestBody Product product) {
        ResponseEntity response = PRODUCT_SERVICE.saveProduct(product);
        return response;
    }
    @PostMapping("corporate-settlement-account/create")
    public ResponseEntity saveRegister(@Valid @RequestBody Register register) {
        ResponseEntity response=REGISTER_SERVICE.saveRegister(register);
        return response;
    }
}
