package br.com.marcoshssilva.springbooteureka.domain.services;

import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import org.springframework.stereotype.Service;

@Service
public interface UserControllerService {
    void resetPasswordFromUsername(final String username, final String newPassword) throws BusinessException;
    User createUser(final String username, final String password, Boolean enabled, String[] roles) throws BusinessException;
}
