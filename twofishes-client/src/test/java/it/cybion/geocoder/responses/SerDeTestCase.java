package it.cybion.geocoder.responses;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class SerDeTestCase {

    @Test
    public void fromFile() throws Exception {

        final ObjectMapper objectMapper = new ObjectMapper();
        final InputStream geocodeResponseIs = this.getClass().getResourceAsStream(
                "/geocoderesponse.json");

        final GeocodeResponse geocodeResponse = objectMapper.readValue(geocodeResponseIs,
                GeocodeResponse.class);
        assertNotNull(geocodeResponse);
        assertEquals(geocodeResponse.getInterpretations().size(), 1);

    }
}
