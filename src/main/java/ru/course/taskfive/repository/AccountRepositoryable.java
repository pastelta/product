package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.Account;
import ru.course.taskfive.entity.AccountPool;

@Repository
public interface AccountRepositoryable extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT a.account_number FROM account a WHERE a.account_pool_id=:accId FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
    String getAccountNumber (@Param("accId") Integer accId);
}
