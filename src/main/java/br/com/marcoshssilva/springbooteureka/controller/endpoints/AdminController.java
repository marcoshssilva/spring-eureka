package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.*;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@lombok.RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error";
    private static final String MSG_PASSWORD_CHANGED = "Password has been changed.";
    private static final String MSG_USER_CREATED = "User created with success.";
    private static final String MSG_USER_UPDATED = "User updated with success.";

    private final UserManagementService userManagementService;

    @Transactional
    @PostMapping("/reset-password")
    SimpleStatusResponseBodyDto resetPasswordAccount(@RequestBody @Valid AdminResetPasswordRequestBodyDto body) {
        try {
            userManagementService.resetPasswordFromUsername(body.username(), body.newPassword());
            return new SimpleStatusResponseBodyDto(MSG_PASSWORD_CHANGED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @PostMapping("/create-user")
    SimpleStatusResponseBodyDto createUser(@RequestBody @Valid AdminCreateUserRequestBodyDto body) {
        try {
            userManagementService.createUser(body.username(), body.password(), body.enabled(), body.roles());
            return new SimpleStatusResponseBodyDto(MSG_USER_CREATED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @PostMapping("/update-user")
    SimpleStatusResponseBodyDto updateUser(@RequestBody @Valid AdminUpdateUserRequestBodyDto body) {
        try {
            userManagementService.updateUser(body.username(), body.roles());
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @PostMapping("/change-password")
    SimpleStatusResponseBodyDto changePassword(@RequestBody @Valid AdminChangePasswordRequestBodyDto body) {
        try {
            userManagementService.changePasswordFromUsername(body.username(), body.newPassword(), body.oldPassword());
            return new SimpleStatusResponseBodyDto(MSG_PASSWORD_CHANGED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @DeleteMapping("/delete-user/{username}")
    SimpleStatusResponseBodyDto updateUser(@PathVariable String username) {
        try {
            userManagementService.deleteUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @PutMapping("/enable-user/{username}")
    SimpleStatusResponseBodyDto enableUser(@PathVariable String username) {
        try {
            userManagementService.enableUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }

    @Transactional
    @PutMapping("/disable-user/{username}")
    SimpleStatusResponseBodyDto disableUser(@PathVariable String username) {
        try {
            userManagementService.disableUser(username);
            return new SimpleStatusResponseBodyDto(MSG_USER_UPDATED, StatusTypeResponse.SUCCESS);
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }
    }
}
