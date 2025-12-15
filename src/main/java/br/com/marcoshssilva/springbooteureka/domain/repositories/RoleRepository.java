package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, RolePK> {
    @Query("SELECT obj FROM Role obj WHERE obj.id.username = ?1")
    Set<Role> findAllByUsername(String username);
}
