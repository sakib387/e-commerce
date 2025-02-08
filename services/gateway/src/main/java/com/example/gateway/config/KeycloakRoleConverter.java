package com.example.gateway.config;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
        if (resourceAccess != null) {
            Map<String, Object> demoRoles = (Map<String, Object>) resourceAccess.get("demo-for-keyclock");
            if (demoRoles != null) {
                List<String> roles = (List<String>) demoRoles.get("roles");
                if (roles != null) {
                    System.out.println(roles);
                    return roles.stream()
                            .map(role -> "ROLE_" + role.toUpperCase()) // Convert "USER" -> "ROLE_USER"
                            .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet());
                }
            }
        }
        return List.of(); // Return empty authorities if no roles found
    }
}
