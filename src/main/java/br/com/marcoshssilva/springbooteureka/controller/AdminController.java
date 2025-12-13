package br.com.marcoshssilva.springbooteureka.controller;

import br.com.marcoshssilva.springbooteureka.domain.services.UserControllerService;
import br.com.marcoshssilva.springbooteureka.http.requests.AdminUpdatePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.http.responses.AdminUpdatePasswordResponseBodyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@lombok.RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserControllerService userControllerService;

    @PostMapping("/reset-password")
    ResponseEntity<AdminUpdatePasswordResponseBodyDto> resetPasswordAccount(@RequestBody AdminUpdatePasswordRequestBodyDto body) {
        try {
            userControllerService.resetPasswordFromUsername(body.username(), body.newPassword());
            return ResponseEntity.ok(new AdminUpdatePasswordResponseBodyDto("Password has been changed.", "SUCCESS"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new AdminUpdatePasswordResponseBodyDto(e.getMessage(), "ERROR"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new AdminUpdatePasswordResponseBodyDto("Internal server error", "ERROR"));
        }

    }
}
