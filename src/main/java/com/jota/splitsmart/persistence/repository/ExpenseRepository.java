package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
