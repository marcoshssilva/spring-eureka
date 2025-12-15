package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminCreateUserRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminUpdatePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import jakarta.validation.Valid;
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
    SimpleStatusResponseBodyDto resetPasswordAccount(@RequestBody @Valid AdminUpdatePasswordRequestBodyDto body) {
        try {
            userControllerService.resetPasswordFromUsername(body.username(), body.newPassword());
            return new SimpleStatusResponseBodyDto("Password has been changed.", "SUCCESS");
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), "ERROR"));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto("Internal server error", "ERROR"));
        }
    }

    @Transactional
    @PostMapping("/create-user")
    SimpleStatusResponseBodyDto createUser(@RequestBody @Valid AdminCreateUserRequestBodyDto body) {
        try {
            userControllerService.createUser(body.username(), body.password(), body.enabled(), body.roles());
            return new SimpleStatusResponseBodyDto("User created with success", "SUCCESS");
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), "ERROR"));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto("Internal server error", "ERROR"));
        }
    }

}
