package com.github.marcoshssilva.eureka.domain.services;

import com.github.marcoshssilva.eureka.domain.entities.User;
import com.github.marcoshssilva.eureka.domain.exceptions.BusinessException;
import org.springframework.stereotype.Service;

@Service
public interface UserManagementService {
    void resetPasswordFromUsername(final String username, final String newPassword) throws BusinessException;
    User getUserByUsername(final String username) throws BusinessException;
    User createUser(final String username, final String password, Boolean enabled, String[] roles) throws BusinessException;
    User updateUser(final String username, final String[] roles) throws BusinessException;
    void changePasswordFromUsername(final String username, final String newPassword, final String oldPassword) throws BusinessException;
    void deleteUser(final String username) throws BusinessException;
    void enableUser(final String username) throws BusinessException;
    void disableUser(final String username) throws BusinessException;
}
