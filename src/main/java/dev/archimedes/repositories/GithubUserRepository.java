package dev.archimedes.repositories;

import dev.archimedes.entities.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubUserRepository extends JpaRepository<GithubUser, Integer> {
}
