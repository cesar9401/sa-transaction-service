package com.cesar31.transaction.infrastructure.adapters.output.security;

import com.cesar31.transaction.application.ports.output.CurrentUserOutputPort;
import com.cesar31.transaction.infrastructure.adapters.input.rest.security.SaAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class CurrentUserAdapter implements CurrentUserOutputPort {

    private SaAuthenticationToken getSaAuthenticationToken() {
        return (SaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UUID getUserId() {
        var authentication = getSaAuthenticationToken();
        return authentication.getUserId();
    }

    @Override
    public UUID getOrganizationId() {
        var authentication = getSaAuthenticationToken();
        return authentication.getOrganizationId();
    }

    @Override
    public Boolean hasRole(UUID roleId) {
        var authentication = getSaAuthenticationToken();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(roleId.toString()));
    }

    @Override
    public Boolean hasAnyRole(Set<UUID> roleIds) {
        var authentication = getSaAuthenticationToken();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> roleIds.contains(UUID.fromString(a.getAuthority())));
    }
}
