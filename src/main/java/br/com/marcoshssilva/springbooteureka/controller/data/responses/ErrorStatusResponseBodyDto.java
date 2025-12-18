package br.com.marcoshssilva.springbooteureka.controller.data.responses;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public record ErrorStatusResponseBodyDto(String message, Collection<ErrorField> errors, StatusTypeResponse status) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorStatusResponseBodyDto that = (ErrorStatusResponseBodyDto) o;
        return Objects.equals(message, that.message) && Objects.equals(errors, that.errors) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, errors, status);
    }

    @Override
    public String toString() {
        return "ErrorStatusResponseBodyDto{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                ", status='" + status + '\'' +
                '}';
    }

    public record ErrorField(String field, String message, Object receivedData) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ErrorField that = (ErrorField) o;
            return Objects.equals(field, that.field) && Objects.equals(message, that.message) && Objects.equals(receivedData, that.receivedData);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field, message, receivedData);
        }
    }

}
