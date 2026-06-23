package br.com.marcoshssilva.springbooteureka.controller.data.responses;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.StatusTypeResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleStatusResponseBodyDtoTests {
    @Test
    void testGetters() {
        SimpleStatusResponseBodyDto dto = new SimpleStatusResponseBodyDto("success", StatusTypeResponse.SUCCESS);

        assertEquals("success", dto.message());
        assertEquals(StatusTypeResponse.SUCCESS, dto.status());
    }

    @Test
    void testEqualsAndHashCode() {
        SimpleStatusResponseBodyDto d1 = new SimpleStatusResponseBodyDto("m", StatusTypeResponse.SUCCESS);
        SimpleStatusResponseBodyDto d2 = new SimpleStatusResponseBodyDto("m", StatusTypeResponse.SUCCESS);
        SimpleStatusResponseBodyDto d3 = new SimpleStatusResponseBodyDto("m2", StatusTypeResponse.SUCCESS);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
    }
}
