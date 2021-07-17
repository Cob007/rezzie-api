package com.rezzie.api.user.volunteerHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerHistoryRepository extends JpaRepository<VolunteerHistory, Integer> {
}
