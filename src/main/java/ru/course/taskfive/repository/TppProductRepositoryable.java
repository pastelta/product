package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.TppProduct;
@Repository
public interface TppProductRepositoryable extends JpaRepository<TppProduct, Integer> {
}
