package dev.archimedes.services.security;

import dev.archimedes.entities.GithubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final GithubUser githubUser;
    private final Map<String, Object> attributes;
    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(githubUser.getUserType().name()));
    }

    @Override
    public String getName() {
        return githubUser.getLogin();
    }
}
