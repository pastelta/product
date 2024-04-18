package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.TppProductRegister;

@Repository
public interface TppRegisterRepositoryable extends JpaRepository<TppProductRegister, Integer> {
}
