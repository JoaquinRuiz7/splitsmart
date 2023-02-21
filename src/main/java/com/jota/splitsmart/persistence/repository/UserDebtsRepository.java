package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.UserDebts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDebtsRepository extends JpaRepository<UserDebts, Long> {

    List<UserDebts> findAllByExpenseId(final Long expenseId);

    @Query(value = "SELECT * FROM user_pays_expense UPE WHERE UPE.user_id = ?1 AND UPE.expense_id = ?2", nativeQuery = true)
    UserDebts findByPayer(final Long payerId, final Long expenseId);

    @Query(value = "SELECT * FROM user_pays_expense UPE WHERE UPE.user_id = ?1", nativeQuery = true)
    List<UserDebts> getUserDebtsExpenses(final Long userId);
}
