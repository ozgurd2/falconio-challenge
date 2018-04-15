package io.falcon.challenge.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.falcon.challenge.dto.DummyProductDTO;
import io.falcon.challenge.dto.DummyProductDTOBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestUtil {

    /**
     * Convert an object to JSON String.
     *
     * @param object the object to convert
     * @return the String json
     * @throws IOException
     */
    public static String toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    @Test
    public void testConvertion() throws IOException {
        DummyProductDTO build = DummyProductDTOBuilder.aDummyProductDTO().productDescription("desc").productName("name").build();
        assertThat(toJson(build)).isEqualTo("{\"productName\":\"name\",\"productDescription\":\"desc\"}");
    }
}
