package br.com.marcoshssilva.springbooteureka.domain.services;

import org.springframework.stereotype.Service;

@Service
public interface UserControllerService {
    void resetPasswordFromUsername(final String username, final String newPassword);
}
