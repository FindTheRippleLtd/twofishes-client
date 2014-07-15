package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.GeocodeResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImplTestCase {

    private GeocoderImpl geocoderImpl;

    @BeforeMethod
    public void setUp() throws Exception {

        this.geocoderImpl = new GeocoderImpl("localhost", 5101, new ObjectMapper());

    }

    @AfterMethod
    public void tearDown() throws Exception {

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
}
