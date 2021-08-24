package com.rezzie.api.user.headline;

import com.rezzie.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeadlineRepository extends JpaRepository<Headline, Integer> {
    Optional<Headline> findByUser(User user);
}
