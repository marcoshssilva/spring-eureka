package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.SpringSessionAttributes;
import com.github.marcoshssilva.eureka.domain.entities.SpringSessionAttributesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSessionAttributesRepository extends JpaRepository<SpringSessionAttributes, SpringSessionAttributesPK> {
}
