package it.cybion.geocoder.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeRequestTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeRequestTestCase.class);

    @Test
    public void givenSameInstancesWhenEqualsThenEqualIsTrue() throws Exception {

        final GeocodeRequest some = new GeocodeRequest();
        final GeocodeRequest any = new GeocodeRequest();
        assertEquals(some, any);

    }
}
