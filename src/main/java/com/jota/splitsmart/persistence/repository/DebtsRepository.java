package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.Debts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DebtsRepository extends JpaRepository<Debts, Long> {

    List<Debts> findAllByExpenseId(final Long expenseId);

    @Query(value = "SELECT debt FROM Debts debt WHERE debt.user.id = ?1 AND debt.expense.id = ?2")
    Debts findByUserAndExpense(final Long payerId, final Long expenseId);

    @Query(value = "SELECT debt FROM Debts debt WHERE debt.user.id = ?1")
    List<Debts> getUserDebts(final Long userId);
}
