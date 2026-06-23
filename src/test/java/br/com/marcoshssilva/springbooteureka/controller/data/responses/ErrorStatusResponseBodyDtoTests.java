package br.com.marcoshssilva.springbooteureka.controller.data.responses;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ErrorStatusResponseBodyDtoTests {
    @Test
    void testGetters() {
        List<ErrorStatusResponseBodyDto.ErrorField> errors = new ArrayList<>();
        ErrorStatusResponseBodyDto.ErrorField field = new ErrorStatusResponseBodyDto.ErrorField("f", "m", "data");
        errors.add(field);

        ErrorStatusResponseBodyDto dto = new ErrorStatusResponseBodyDto("error", errors, StatusTypeResponse.ERROR);

        assertEquals("error", dto.message());
        assertEquals(StatusTypeResponse.ERROR, dto.status());
        assertEquals(1, dto.errors().size());
        
        ErrorStatusResponseBodyDto.ErrorField f = dto.errors().iterator().next();
        assertEquals("f", f.field());
        assertEquals("m", f.message());
        assertEquals("data", f.receivedData());
    }

    @Test
    void testEqualsAndHashCode() {
        ErrorStatusResponseBodyDto d1 = new ErrorStatusResponseBodyDto("m", null, StatusTypeResponse.ERROR);
        ErrorStatusResponseBodyDto d2 = new ErrorStatusResponseBodyDto("m", null, StatusTypeResponse.ERROR);
        ErrorStatusResponseBodyDto d3 = new ErrorStatusResponseBodyDto("m2", null, StatusTypeResponse.ERROR);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
    }
    
    @Test
    void testErrorFieldEqualsAndHashCode() {
        ErrorStatusResponseBodyDto.ErrorField f1 = new ErrorStatusResponseBodyDto.ErrorField("f", "m", null);
        ErrorStatusResponseBodyDto.ErrorField f2 = new ErrorStatusResponseBodyDto.ErrorField("f", "m", null);
        ErrorStatusResponseBodyDto.ErrorField f3 = new ErrorStatusResponseBodyDto.ErrorField("f2", "m", null);

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
    }
}
