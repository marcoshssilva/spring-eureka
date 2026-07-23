package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.Role;
import com.github.marcoshssilva.eureka.domain.entities.RolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, RolePK> {
    @Query("SELECT obj FROM Role obj WHERE obj.id.user.username = ?1")
    Set<Role> findAllByUsername(String username);
    @Modifying
    void deleteAllById_User_Username(String username);
}
