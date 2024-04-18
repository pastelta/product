package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.Agreement;
@Repository
public interface AgreementRepositoryable extends JpaRepository<Agreement, Integer> {
}
