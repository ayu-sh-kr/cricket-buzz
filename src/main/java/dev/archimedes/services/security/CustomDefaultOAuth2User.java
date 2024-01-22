package dev.archimedes.services.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomDefaultOAuth2User extends DefaultOAuth2User {

    public CustomDefaultOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        super(authorities, attributes, nameAttributeKey);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return super.getAttributes();
    }
}
