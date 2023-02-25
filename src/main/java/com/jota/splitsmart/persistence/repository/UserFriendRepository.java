package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.UserFriend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    boolean existsByUserIdAndFriendId(final Long userId, final Long friendId);

    List<UserFriend> findAllByUserId(final Long userId);

}
