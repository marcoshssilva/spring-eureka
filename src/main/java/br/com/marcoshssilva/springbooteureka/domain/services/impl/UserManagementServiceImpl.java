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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Service
@lombok.RequiredArgsConstructor
public final class UserManagementServiceImpl implements UserManagementService {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String MSG_USERNAME_NOT_FOUND = "Username not found in database";
    private static final String MSG_USERNAME_ALREADY_EXISTS = "Username already exists in database.";
    private static final String MSG_PASSWORD_DOESNT_MATCH = "Password doesn't match. Check credentials.";

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

        User user = new User(username, passwordEncoder.encode(password), enabled, null);
        Set<Role> roleSet = Stream.of(roles).map(role -> new Role(new RolePK(user, formatRole(role)))).collect(Collectors.toSet());
        userRepository.save(user);
        user.setRoles(roleSet);
        roleRepository.saveAll(roleSet);

        return user;
    }

    @Override
    public User updateUser(final String username, final String[] roles) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        roleRepository.deleteAll(user.getRoles());
        user.setRoles(new HashSet<>(roleRepository.saveAll(
                Arrays.stream(roles).map(role -> new Role(new RolePK(user, formatRole(role)))).toList()
        )));

        return userRepository.save(user);
    }

    @Override
    public void changePasswordFromUsername(final String username, final String newPassword, final String oldPassword) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BusinessException(MSG_PASSWORD_DOESNT_MATCH);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(final String username) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(MSG_USERNAME_NOT_FOUND));
        roleRepository.deleteAllById_User_Username(username);
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

    private String formatRole(String role) {
        if (role.startsWith(ROLE_PREFIX)) {
            return role;
        }
        return ROLE_PREFIX.concat(role);
    }
}
