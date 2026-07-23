package com.github.marcoshssilva.eureka.controller.endpoints;

import com.github.marcoshssilva.eureka.controller.data.etc.RequestStepsAction;
import com.github.marcoshssilva.eureka.controller.data.etc.StatusTypeResponse;
import com.github.marcoshssilva.eureka.controller.data.responses.SimpleStatusResponseBodyDto;
import com.github.marcoshssilva.eureka.controller.exceptions.BadRequestException;
import com.github.marcoshssilva.eureka.controller.exceptions.InternalServerErrorException;
import com.github.marcoshssilva.eureka.domain.exceptions.BusinessException;

public abstract class AbstractApiController {
    protected static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error.";
    protected static final String MSG_PASSWORD_CHANGED = "Password has been changed.";
    protected static final String MSG_USER_CREATED = "User created with success.";
    protected static final String MSG_USER_UPDATED = "User updated with success.";
    protected static final String MSG_USER_DELETED = "User deleted with success.";

    protected SimpleStatusResponseBodyDto processRequest(RequestStepsAction action) {
        try {
            return action.action();
        } catch (BusinessException e) {
            throw new BadRequestException(new SimpleStatusResponseBodyDto(e.getMessage(), StatusTypeResponse.ERROR));
        } catch (Exception _) {
            throw new InternalServerErrorException(new SimpleStatusResponseBodyDto(MSG_INTERNAL_SERVER_ERROR, StatusTypeResponse.ERROR));
        }

    }
}
