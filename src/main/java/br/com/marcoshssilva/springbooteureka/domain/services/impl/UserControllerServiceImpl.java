package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.repositories.UserRepository;
import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
@lombok.RequiredArgsConstructor
public class UserControllerServiceImpl implements UserControllerService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPasswordFromUsername(final String username, final String newPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found in database"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
