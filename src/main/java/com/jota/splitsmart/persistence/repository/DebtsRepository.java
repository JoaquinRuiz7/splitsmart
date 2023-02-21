package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.Debts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DebtsRepository extends JpaRepository<Debts, Long> {

    List<Debts> findAllByExpenseId(final Long expenseId);

    @Query(value = "SELECT * FROM debts ud WHERE ud.user_id = ?1 AND ud.expense_id = ?2", nativeQuery = true)
    Debts findByUserAndExpense(final Long payerId, final Long expenseId);

    @Query(value = "SELECT * FROM debts ud WHERE ud.user_id = ?1", nativeQuery = true)
    List<Debts> getUserDebts(final Long userId);
}
