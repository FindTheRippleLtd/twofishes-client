package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.Flag;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.serialization.FlagDeserializer;
import it.cybion.geocoder.serialization.FlagSerializer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImplTestCase {

    private GeocoderImpl geocoderImpl;

    private CloseableHttpClient closeable;

    @BeforeMethod
    public void setUp() throws Exception {

        final ObjectMapper objectMapper = new ObjectMapper();

        final SimpleModule flagDeserializationModule = new SimpleModule("FlagModule", new Version(1,
                0, 0, null)).addDeserializer(Flag.class, new FlagDeserializer()).addSerializer(
                Flag.class, new FlagSerializer());

        objectMapper.registerModule(flagDeserializationModule);
        this.closeable = HttpClients.createDefault();
        this.geocoderImpl = new GeocoderImpl("localhost", 5101, objectMapper, closeable);

    }

    @AfterMethod
    public void tearDown() throws Exception {

        this.closeable.close();
        this.geocoderImpl = null;

    }

    @Test
    public void givenDefaultAndEnglishQueriesShouldBeSame() throws Exception {

        final GeocodeRequest aRequestDefaultLang = new GeocodeRequest("nyc");
        final GeocodeResponse response = this.geocoderImpl.geocode(aRequestDefaultLang);
        assertNotNull(response);

        final GeocodeRequest aRequestEnglishLang = new GeocodeRequest("nyc", "en");
        final GeocodeResponse response1 = this.geocoderImpl.geocode(aRequestEnglishLang);
        assertNotNull(response1);
        assertEquals(response, response1);
    }

    @Test
    public void givenAStreetShouldNot() throws Exception {

        final GeocodeRequest aRequestDefaultLang = new GeocodeRequest.GeocodeRequestBuilder().query(
                "via trionfale").countryCode("IT").lang("en").build();

        final GeocodeResponse response = this.geocoderImpl.geocode(aRequestDefaultLang);
        assertNotNull(response);
        assertEquals(response.getInterpretations().size(), 0);

    }

    @Test
    public void givenUseCaseShouldWork() throws Exception {

        assertTrue(true);

    }
}
