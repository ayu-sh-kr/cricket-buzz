package dev.archimedes.entities;

import dev.archimedes.enums.AccountStatus;
import dev.archimedes.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "USER")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private long phone;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private UserType userType = UserType.USER;

    @Column(name = "IS_ACTIVE")
    private boolean active = true;

    @Column(name = "ACCOUNT_STATUS")
    private AccountStatus accountStatus = AccountStatus.ENABLED;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", dateOfBirth=" + dateOfBirth +
                ", userType=" + userType +
                ", active=" + active +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
