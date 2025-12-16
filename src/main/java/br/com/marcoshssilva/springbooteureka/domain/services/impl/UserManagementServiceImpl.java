package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.repositories.RoleRepository;
import br.com.marcoshssilva.springbooteureka.domain.repositories.UserRepository;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Service
@lombok.RequiredArgsConstructor
public final class UserManagementServiceImpl implements UserManagementService {
    private static final String MSG_USERNAME_NOT_FOUND = "Username not found in database";
    private static final String MSG_USERNAME_ALREADY_EXISTS = "Username already exists in database.";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPasswordFromUsername(final String username, final String newPassword) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User createUser(final String username, final String password, final Boolean enabled, final String[] roles) throws BusinessException {
        Optional<User> usernameSearch = userRepository.findByUsername(username);
        if (usernameSearch.isPresent()) {
            throw new BusinessException(MSG_USERNAME_ALREADY_EXISTS);
        }

        User user = new User(username, passwordEncoder.encode(password), enabled);
        userRepository.save(user);
        Stream.of(roles).forEach(role -> roleRepository.save(new Role(new RolePK(user.getUsername(), role))));

        return user;
    }

    @Override
    public User updateUser(final String username, final String newPassword, final String[] roles) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPassword));
        Set<Role> newRoles = Arrays.stream(roles).map(role -> new Role(new RolePK(username, role))).collect(Collectors.toSet());
        Set<Role> oldSet = roleRepository.findAllByUsername(username);

        roleRepository.deleteAll(oldSet);
        roleRepository.saveAll(newRoles);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final String username) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        roleRepository.deleteAllById_Username(username);
        userRepository.delete(user);
    }

    @Override
    public void enableUser(final String username) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        user.setEnabled(Boolean.TRUE);
        userRepository.save(user);
    }

    @Override
    public void disableUser(final String username) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        user.setEnabled(Boolean.FALSE);
        userRepository.save(user);
    }
}
