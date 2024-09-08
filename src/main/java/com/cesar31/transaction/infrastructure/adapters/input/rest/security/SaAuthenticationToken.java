package com.cesar31.transaction.infrastructure.adapters.input.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class SaAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final UUID userId;
    private final UUID organizationId;

    public SaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UUID userId, UUID organizationId) {
        super(principal, credentials, authorities);
        this.userId = userId;
        this.organizationId = organizationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }
}
