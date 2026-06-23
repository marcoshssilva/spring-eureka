package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.SpringSessionAttributes;
import br.com.marcoshssilva.springbooteureka.domain.entities.SpringSessionAttributesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSessionAttributesRepository extends JpaRepository<SpringSessionAttributes, SpringSessionAttributesPK> {
}
