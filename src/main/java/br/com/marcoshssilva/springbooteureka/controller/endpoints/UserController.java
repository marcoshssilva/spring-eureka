package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.UserChangePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@lombok.RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractApiController {
    private final UserManagementService userManagementService;

    @Transactional
    @PostMapping("/change-password")
    public SimpleStatusResponseBodyDto changePassword(@RequestBody @Valid UserChangePasswordRequestBodyDto body, @AuthenticationPrincipal User user) {
        return processRequest(() -> {
            userManagementService.changePasswordFromUsername(user.getUsername(), body.newPassword(), body.oldPassword());
           return new SimpleStatusResponseBodyDto(MSG_PASSWORD_CHANGED, StatusTypeResponse.SUCCESS);
        });
    }

}
