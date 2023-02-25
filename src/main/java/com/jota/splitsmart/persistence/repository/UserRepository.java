package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM \"user\" WHERE id IN (?1)", nativeQuery = true)
    List<User> findMultipleById(final List<Long> ids);

    Optional<User> findByEmail(final String email);

    Optional<User> findByEmailOrCellphone(final String email, final String cellphone);

    Optional<User> findByCellphone(final String cellphone);

    boolean existsByEmail(final String email);

}
