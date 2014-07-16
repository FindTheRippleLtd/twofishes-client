package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.Flag;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.responses.Interpretation;
import it.cybion.geocoder.serialization.FlagDeserializer;
import it.cybion.geocoder.serialization.FlagSerializer;
import it.cybion.geocoder.serialization.YahooWoeTypeDeserializer;
import it.cybion.geocoder.serialization.YahooWoeTypeSerializer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImplIntegrationTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            GeocoderImplIntegrationTestCase.class);

    private GeocoderImpl geocoderImpl;

    private CloseableHttpClient closeable;

    @BeforeMethod
    public void setUp() throws Exception {

        final ObjectMapper objectMapper = new ObjectMapper();

        final SimpleModule flagDeserializationModule = new SimpleModule("GeocoderModule",
                new Version(1, 0, 0, null)).addDeserializer(Flag.class, new FlagDeserializer())
                .addSerializer(Flag.class, new FlagSerializer())
                .addDeserializer(YahooWoeType.class, new YahooWoeTypeDeserializer())
                .addSerializer(YahooWoeType.class, new YahooWoeTypeSerializer());

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
        final GeocodeResponse nycResponse = this.geocoderImpl.geocode(aRequestDefaultLang);
        assertNotNull(nycResponse);

        final GeocodeRequest aRequestEnglishLang = new GeocodeRequest("nyc", "en");
        final GeocodeResponse response1 = this.geocoderImpl.geocode(aRequestEnglishLang);
        assertNotNull(response1);
        assertEquals(nycResponse, response1);
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

        //should substitute Location location and LatLon latitude

    }

    @Test
    public void shouldReturnLocationWithCountryAndProvince() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "Rome, Italy").addWoeHint(YahooWoeType.ADMIN2).build();

        final GeocodeResponse response = this.geocoderImpl.geocode(locationRequest);

        for (final Interpretation interpretation : response.getInterpretations()) {
            final YahooWoeType woeType = interpretation.getFeature().getWoeType();
            LOGGER.info(woeType + "");
            if (woeType.equals(YahooWoeType.ADMIN2)) {
                LOGGER.info(interpretation + "");
                //                LOGGER.info("center: " + interpretation.getGeometry().getCenter());
                LOGGER.info("geometry center: " +
                            interpretation.getFeature().getGeometry().getCenter());
            }

        }

    }
}
