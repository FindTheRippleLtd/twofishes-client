package it.cybion.geocoder.serialization;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class ObjectMapperFactoryTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperFactoryTestCase.class);

    @Test
    public void test() throws Exception {

        final ObjectMapper one = ObjectMapperFactory.INSTANCE.getObjectMapper();
        final ObjectMapper another = ObjectMapperFactory.INSTANCE.getObjectMapper();
        assertEquals(one, another);

    }
}
