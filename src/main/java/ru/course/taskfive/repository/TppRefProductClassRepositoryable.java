package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.TppRefProductClass;

@Repository
public interface TppRefProductClassRepositoryable extends JpaRepository<TppRefProductClass, Integer> {
}
