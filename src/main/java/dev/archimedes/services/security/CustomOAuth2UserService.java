package dev.archimedes.services.security;

import dev.archimedes.entities.GithubUser;
import dev.archimedes.enums.UserType;
import dev.archimedes.repositories.GithubUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final GithubUserRepository githubUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        System.out.println(attributes);

        int userId = (Integer) attributes.get("id");
        String name = (String) attributes.get("login");
        String email = (String) attributes.get("email");
        String node_id= (String) attributes.get("node_id");

        System.out.println(userId);

        GithubUser user = githubUserRepository.findById(userId).orElseGet(() -> {
            GithubUser newUser = GithubUser.builder()
                    .id(userId)
                    .login(name)
                    .email(email)
                    .node_id(node_id)
                    .userType(UserType.USER)
                    .build();
            return githubUserRepository.save(newUser);
        });
        System.out.println(user);
        return new DefaultOAuth2User(Set.of(new SimpleGrantedAuthority(user.getUserType().name())), attributes, "login");
    }
}
