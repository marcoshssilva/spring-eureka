package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Primary
@Service
@lombok.RequiredArgsConstructor
public class UserControllerServiceImpl implements UserControllerService {
    private final UserDetailsManager userDetailsManager;
}
