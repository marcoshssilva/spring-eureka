package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.RequestStepsAction;
import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.BadRequestException;
import br.com.marcoshssilva.springbooteureka.controller.exceptions.InternalServerErrorException;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public abstract class AbstractApiController {
    protected static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error.";
    protected static final String MSG_PASSWORD_CHANGED = "Password has been changed.";
    protected static final String MSG_USER_CREATED = "User created with success.";
    protected static final String MSG_USER_UPDATED = "User updated with success.";

    protected SimpleStatusResponseBodyDto processRequest(RequestStepsAction action) {
        try {
            return action.action();
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception e) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }

    }
}
