package ru.course.taskfive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String status;
    private String message;
    private Integer instanceId;
    private List<Integer> agreementIdList;
    private Integer registerId;
}
