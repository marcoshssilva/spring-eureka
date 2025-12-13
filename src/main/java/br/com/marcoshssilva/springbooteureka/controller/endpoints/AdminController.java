package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import br.com.marcoshssilva.springbooteureka.http.requests.AdminUpdatePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.http.responses.AdminUpdatePasswordResponseBodyDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@lombok.RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserControllerService userControllerService;

    @Transactional
    @PostMapping("/reset-password")
    AdminUpdatePasswordResponseBodyDto resetPasswordAccount(@RequestBody AdminUpdatePasswordRequestBodyDto body) {
        try {
            userControllerService.resetPasswordFromUsername(body.username(), body.newPassword());
            return new AdminUpdatePasswordResponseBodyDto("Password has been changed.", "SUCCESS");
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException(new AdminUpdatePasswordResponseBodyDto(e.getMessage(), "ERROR"));
        } catch (Exception e) {
            throw new InternalServerErrorException(new AdminUpdatePasswordResponseBodyDto("Internal server error", "ERROR"));
        }
    }

}
