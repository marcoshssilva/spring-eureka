package com.github.marcoshssilva.eureka.controller.data.etc;

import com.github.marcoshssilva.eureka.controller.data.responses.SimpleStatusResponseBodyDto;
import com.github.marcoshssilva.eureka.domain.exceptions.BusinessException;

@FunctionalInterface
public interface RequestStepsAction {
    SimpleStatusResponseBodyDto action() throws BusinessException;
}
