package com.example.ecommerce.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role implements GrantedAuthoritiesContainer {

    USER(Set.of(Permission.USER)),
    ADMIN(Set.of(
            Permission.ADMIN_CREATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_READ,
            Permission.VENDOR_CREATE,
            Permission.VENDOR_DELETE,
            Permission.VENDOR_UPDATE,
            Permission.VENDOR_READ
    )),
    VENDOR(Set.of(
            Permission.VENDOR_CREATE,
            Permission.VENDOR_DELETE,
            Permission.VENDOR_UPDATE,
            Permission.VENDOR_READ
    ));

    @Getter
    private final Set<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        var authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(
                        permission.name()
                ))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}
