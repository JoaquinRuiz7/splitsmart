package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.UserPaysExpense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPaysExpenseRepository extends JpaRepository<UserPaysExpense, Long> {

    List<UserPaysExpense> findAllByExpenseId(final Long expenseId);

    @Query(value = "SELECT * FROM user_pays_expense UPE WHERE UPE.user_id = ?1 AND UPE.expense_id = ?2",nativeQuery = true)
    UserPaysExpense findByPayer(final Long payerId, final Long expenseId);
}
