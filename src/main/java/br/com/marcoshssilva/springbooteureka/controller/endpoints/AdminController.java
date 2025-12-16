package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.*;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@lombok.RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController extends AbstractApiController {
    private final UserManagementService userManagementService;

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
            userManagementService.createUser(body.username(), body.password(), body.enabled(), body.roles());
            return new SimpleStatusResponseBodyDto(MSG_USER_CREATED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @PostMapping("/update-user")
    SimpleStatusResponseBodyDto updateUser(@RequestBody @Valid AdminUpdateUserRequestBodyDto body) {
        return processRequest(() -> {
            userManagementService.updateUser(body.username(), body.roles());
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        });
    }

    @Transactional
    @DeleteMapping("/delete-user/{username}")
    SimpleStatusResponseBodyDto updateUser(@PathVariable String username) {
        return processRequest(() -> {
            userManagementService.deleteUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
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
