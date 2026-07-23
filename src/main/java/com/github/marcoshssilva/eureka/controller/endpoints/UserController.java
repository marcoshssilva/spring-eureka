package com.github.marcoshssilva.eureka.controller.endpoints;

import com.github.marcoshssilva.eureka.controller.data.etc.StatusTypeResponse;
import com.github.marcoshssilva.eureka.controller.data.requests.UserChangePasswordRequestBodyDto;
import com.github.marcoshssilva.eureka.controller.data.responses.SimpleStatusResponseBodyDto;
import com.github.marcoshssilva.eureka.domain.entities.User;
import com.github.marcoshssilva.eureka.domain.services.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractApiController {
    private final UserManagementService userManagementService;

    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Transactional
    @PostMapping("/change-password")
    public SimpleStatusResponseBodyDto changePassword(@RequestBody @Valid UserChangePasswordRequestBodyDto body, @AuthenticationPrincipal User user) {
        return processRequest(() -> {
            userManagementService.changePasswordFromUsername(user.getUsername(), body.newPassword(), body.oldPassword());
           return new SimpleStatusResponseBodyDto(MSG_PASSWORD_CHANGED, StatusTypeResponse.SUCCESS);
        });
    }

}
