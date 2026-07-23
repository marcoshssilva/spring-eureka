package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.SpringSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSessionRepository extends JpaRepository<SpringSession, String> {
}
