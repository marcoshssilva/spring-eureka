package com.github.marcoshssilva.eureka.controller.endpoints;

import com.github.marcoshssilva.eureka.controller.data.etc.StatusTypeResponse;
import com.github.marcoshssilva.eureka.controller.data.etc.UserRoles;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.*;
import com.github.marcoshssilva.eureka.controller.data.requests.AdminCreateUserRequestBodyDto;
import com.github.marcoshssilva.eureka.controller.data.requests.AdminResetPasswordRequestBodyDto;
import com.github.marcoshssilva.eureka.controller.data.requests.AdminUpdateUserRequestBodyDto;
import com.github.marcoshssilva.eureka.domain.services.UserManagementService;
import com.github.marcoshssilva.eureka.controller.data.responses.SimpleStatusResponseBodyDto;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends AbstractApiController {
    private final UserManagementService userManagementService;

    public AdminController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Transactional
    @PostMapping("/reset-password")
    SimpleStatusResponseBodyDto resetPasswordAccount(@RequestBody @Valid AdminResetPasswordRequestBodyDto body) {
        return processRequest(() -> {
            userManagementService.resetPasswordFromUsername(body.username(), body.newPassword());
            return new SimpleStatusResponseBodyDto(MSG_PASSWORD_CHANGED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @PostMapping("/create-user")
    SimpleStatusResponseBodyDto createUser(@RequestBody @Valid AdminCreateUserRequestBodyDto body) {
        return processRequest(() -> {
            userManagementService.createUser(body.username(), body.password(), body.enabled(), Arrays.stream(body.roles()).map(UserRoles::getAuthority).toArray(String[]::new));
            return new SimpleStatusResponseBodyDto(MSG_USER_CREATED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @PostMapping("/update-user")
    SimpleStatusResponseBodyDto updateUser(@RequestBody @Valid AdminUpdateUserRequestBodyDto body) {
        return processRequest(() -> {
            userManagementService.updateUser(body.username(), Arrays.stream(body.roles()).map(UserRoles::getAuthority).toArray(String[]::new));
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @DeleteMapping("/delete-user/{username}")
    SimpleStatusResponseBodyDto deleteUser(@PathVariable String username) {
        return processRequest(() -> {
            userManagementService.deleteUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_DELETED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @PutMapping("/enable-user/{username}")
    SimpleStatusResponseBodyDto enableUser(@PathVariable String username) {
        return processRequest(() -> {
            userManagementService.enableUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @PutMapping("/disable-user/{username}")
    SimpleStatusResponseBodyDto disableUser(@PathVariable String username) {
        return processRequest(() -> {
            userManagementService.disableUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        });
    }
}
