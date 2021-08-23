package com.rezzie.api.user.contact;

import com.rezzie.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInformationRepository
extends JpaRepository<ContactInformation, Integer> {
    Optional<User> findByUserId(int id);

}
