package br.com.marcoshssilva.springbooteureka.controller.data.etc;

import br.com.marcoshssilva.springbooteureka.controller.data.responses.SimpleStatusResponseBodyDto;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;

@FunctionalInterface
public interface RequestStepsAction {
    SimpleStatusResponseBodyDto action() throws BusinessException;
}
