package ru.course.taskfive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.course.taskfive.entity.AccountPool;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountPoolRepositoryable extends JpaRepository<AccountPool, Integer> {
    @Query(value = "SELECT * FROM account_pool ap WHERE ap.branch_code=:branchCode AND ap.currency_code=:currencyCode", nativeQuery = true)
    AccountPool getAcc (@Param("branchCode") String branchCode, @Param("currencyCode") String currencyCode);
}
