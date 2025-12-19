package br.com.marcoshssilva.springbooteureka.internal.exceptions;

import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {
    @DisplayName("Should create BusinessException with message only")
    @Test
    void testConstructorWithMessage() {
        String message = "Business rule violated";

        BusinessException exception = new BusinessException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @DisplayName("Should create BusinessException with message and cause")
    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Business rule violated";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        BusinessException exception = new BusinessException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @DisplayName("BusinessException should be instance of Exception")
    @Test
    void testExceptionType() {
        BusinessException exception = new BusinessException("Error");
        assertTrue(exception instanceof Exception);
    }
}
