package com.jota.splitsmart.security;

import com.jota.splitsmart.exception.ExpenseNotFoundException;
import com.jota.splitsmart.exception.ForbiddenException;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.persistence.repository.FriendRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityGuard {

    private final JWT jwt;
    private final HttpServletRequest httpServletRequest;
    private final ExpenseRepository expenseRepository;
    private final FriendRequestRepository friendRequestRepository;

    private static final String FORBIDDEN = "Forbidden";

    public void checkIfTokenBelongsToUser(final Long userId) {
        final String token = getTokenFromRequest(httpServletRequest);
        final Long tokenId = jwt.getUserIdFromToken(token);
        if (!userId.equals(tokenId)) {
            throw new ForbiddenException(FORBIDDEN);
        }
    }

    public void checkIfDebtBelongsToUser(final Long id) {
        final String token = getTokenFromRequest(httpServletRequest);
        final Long tokenId = jwt.getUserIdFromToken(token);

        final Expense expense = expenseRepository.findById(id).orElseThrow(() -> {
            throw new ExpenseNotFoundException("Not found");
        });

        if (!expense.getUser().getId().equals(tokenId)) {
            throw new ForbiddenException(FORBIDDEN);
        }
    }

    private String getTokenFromRequest(final HttpServletRequest request) {
        final String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

    public void checkIfFriendRequestBelongsToUser(final Long friendRequestId) {
        final String token = getTokenFromRequest(httpServletRequest);
        final Long tokenId = jwt.getUserIdFromToken(token);

        friendRequestRepository.findById(friendRequestId).ifPresent(friendRequest -> {
            User user = friendRequest.getUser();
            if (!user.getId().equals(tokenId)) {
                throw new ForbiddenException(FORBIDDEN);
            }
        });
    }
}
