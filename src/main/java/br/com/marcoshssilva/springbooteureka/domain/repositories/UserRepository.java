package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    String CACHE_NAMING = "cache_users";

    @Cacheable(cacheNames = CACHE_NAMING, key = "#username")
    Optional<User> findById(String username);
    @Cacheable(cacheNames = CACHE_NAMING, key = "#username")
    Optional<User> findByUsername(String username);
    @CacheEvict(cacheNames = CACHE_NAMING, key = "#entity.username")
    @Modifying
    <S extends User> S save(S entity);
    @CacheEvict(cacheNames = CACHE_NAMING, allEntries = true)
    <S extends User> List<S> saveAll(Iterable<S> entities);
    @CacheEvict(cacheNames = CACHE_NAMING, key = "#entity.username")
    @Modifying
    void delete(User entity);
}
