package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.FriendRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query(value = "SELECT fr FROM FriendRequest fr WHERE fr.friend.id = ?1")
    List<FriendRequest> getReceivedFriendRequests(final Long userId);
}
