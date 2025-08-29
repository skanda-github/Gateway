package com.skanda.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthConverter() {
        return new ReactiveJwtAuthenticationConverterAdapter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            // Extract realm_access.roles
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && realmAccess.get("roles") instanceof Collection) {
                Collection<String> roles = (Collection<String>) realmAccess.get("roles");
                authorities.addAll(
                        roles.stream()
                                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                                .map(SimpleGrantedAuthority::new)
                                .toList()
                );
            }

            JwtAuthenticationConverter baseConverter = new JwtAuthenticationConverter();
            baseConverter.setJwtGrantedAuthoritiesConverter(jwt1 -> authorities);

            return baseConverter.convert(jwt);
        });
    }
}