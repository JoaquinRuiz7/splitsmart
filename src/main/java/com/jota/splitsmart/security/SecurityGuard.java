package com.jota.splitsmart.security;

import com.jota.splitsmart.exception.ExpenseNotFoundException;
import com.jota.splitsmart.exception.ForbiddenException;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityGuard {

    private final JWT jwt;
    private final HttpServletRequest httpServletRequest;
    private final ExpenseRepository expenseRepository;

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
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
