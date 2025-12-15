package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.repositories.RoleRepository;
import br.com.marcoshssilva.springbooteureka.domain.repositories.UserRepository;
import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Primary
@Service
@lombok.RequiredArgsConstructor
public class UserControllerServiceImpl implements UserControllerService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPasswordFromUsername(final String username, final String newPassword) throws BusinessException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException("Username not found in database"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User createUser(String username, String password, Boolean enabled, String[] roles) throws BusinessException {
        Optional<User> usernameSearch = userRepository.findByUsername(username);
        if (usernameSearch.isPresent()) {
            throw new BusinessException("Username already exists in database.");
        }

        User user = new User(username, passwordEncoder.encode(password), enabled);
        userRepository.save(user);
        Stream.of(roles).forEach(role -> roleRepository.save(new Role(new RolePK(user.getUsername(), role))));

        return user;
    }
}
