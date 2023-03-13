package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.UserFriend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    boolean existsByUserIdAndFriendId(final Long userId, final Long friendId);

    List<UserFriend> findAllByUserId(final Long userId);

    @Modifying
    @Query("DELETE FROM UserFriend UE WHERE UE.friend.id = ?1")
    void deleteFriend(final Long friendId);

}
