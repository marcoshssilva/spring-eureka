package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.SpringSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSessionRepository extends JpaRepository<SpringSession, String> {
}
