package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.Feature;
import it.cybion.geocoder.responses.GeocodeResponse;
import it.cybion.geocoder.serialization.ObjectMapperFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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

        this.closeable = HttpClients.createDefault();
        this.geocoderImpl = new GeocoderImpl("localhost", 5101,
                ObjectMapperFactory.INSTANCE.getObjectMapper(), closeable);

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
    public void givenRomeShouldReturnProvinceWithCountryAndLatLon() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "Rome, Italy").addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                ResponseIncludes.PARENTS).build();

        final GeocodeResponse response = this.geocoderImpl.geocode(locationRequest);

        assertEquals(response.getInterpretations().size(), 2);
        final Feature romeFeature = response.getInterpretations().get(0).getFeature();

        assertEquals(romeFeature.getName(), "Rome");
        assertEquals(romeFeature.getGeometry().getCenter(), new GeocodePoint(41.96667D, 12.66667D));
        assertEquals(response.getInterpretations().get(0).getParents().get(1).getName(), "Italy");

    }

    @Test
    public void givenNettunoShouldReturnProvinceWithCountryAndLatLon() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "Nettuno").addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                ResponseIncludes.PARENTS).build();

        final GeocodeResponse response = this.geocoderImpl.geocode(locationRequest);

        assertEquals(response.getInterpretations().size(), 1);
        final Feature nettunoFeature = response.getInterpretations().get(0).getFeature();

        assertEquals(nettunoFeature.getName(), "Nettuno");
        assertEquals(nettunoFeature.getGeometry().getCenter(), new GeocodePoint(41.45907,
                12.66037D));
        assertEquals(response.getInterpretations().get(0).getParents().get(3).getName(), "Italy");

    }
}
