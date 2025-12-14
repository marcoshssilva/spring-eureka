package br.com.marcoshssilva.springbooteureka.controller.data.responses;

import java.io.Serializable;
import java.util.Objects;

public record SimpleStatusResponseBodyDto(String message, String status) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStatusResponseBodyDto that = (SimpleStatusResponseBodyDto) o;
        return Objects.equals(message, that.message) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status);
    }

    @Override
    public String toString() {
        return "AdminUpdatePasswordResponseBodyDto{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
