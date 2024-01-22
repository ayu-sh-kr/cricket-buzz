package dev.archimedes.repositories;

import dev.archimedes.entities.User;
import dev.archimedes.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u.id from USER u where u.email = ?1")
    int getIdByEmail(String email);

    @Transactional
    @Modifying
    @Query("update USER u set u.name = ?1, u.phone = ?2, u.dateOfBirth = ?3 where u.id = ?4")
    int updateNameAndPhoneAndDateOfBirthById(String name, long phone, LocalDate dateOfBirth, int id);

    @Transactional
    @Modifying
    @Query("update USER u set u.accountStatus = ?1 where u.id = ?2")
    int updateAccountStatusById(AccountStatus accountStatus, int id);

}
