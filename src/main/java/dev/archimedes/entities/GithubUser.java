package dev.archimedes.entities;

import dev.archimedes.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "GITHUB_USER")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GithubUser {

    @Id
    private int id;
    private String login;
    private String node_id;
    private String email;
    private UserType userType = UserType.USER;

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", node_id='" + node_id + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                '}';
    }
}
