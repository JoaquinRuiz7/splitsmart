package com.jota.splitsmart.persistence.repository;

import com.jota.splitsmart.persistence.model.FriendRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query(value = "SELECT * FROM friend_requests fr WHERE fr.friend_id = ?1", nativeQuery = true)
    List<FriendRequest> getReceivedFriendRequests(final Long userId);
}
