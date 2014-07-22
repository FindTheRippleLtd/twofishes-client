package it.cybion.geocoder;

import it.cybion.geocoder.exceptions.GeocoderException;
import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.requests.ResponseIncludes;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.Feature;
import it.cybion.geocoder.responses.GeocodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderImplIntegration extends GeocoderImplProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderImplIntegration.class);

    @Test
    public void givenDefaultAndEnglishQueriesShouldBeSame() throws Exception {

        final GeocodeRequest aRequestDefaultLang = new GeocodeRequest("nyc");
        final GeocodeResponse nycResponse = this.geocoder.geocode(aRequestDefaultLang);
        assertNotNull(nycResponse);

        final GeocodeRequest aRequestEnglishLang = new GeocodeRequest("nyc", "en");
        final GeocodeResponse response1 = this.geocoder.geocode(aRequestEnglishLang);
        assertNotNull(response1);
        assertEquals(nycResponse, response1);
    }

    @Test
    public void givenAStreetShouldNot() throws Exception {

        final GeocodeRequest aRequestDefaultLang = new GeocodeRequest.GeocodeRequestBuilder().query(
                "via trionfale").countryCode("IT").lang("en").build();

        final GeocodeResponse response = this.geocoder.geocode(aRequestDefaultLang);
        assertNotNull(response);
        assertEquals(response.getInterpretations().size(), 0);

    }

    @Test
    public void givenRomeShouldReturnProvinceWithCountryAndLatLon() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "Rome, Italy").addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                ResponseIncludes.PARENTS).build();

        final GeocodeResponse response = this.geocoder.geocode(locationRequest);

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

        final GeocodeResponse response = this.geocoder.geocode(locationRequest);

        assertEquals(response.getInterpretations().size(), 1);
        final Feature nettunoFeature = response.getInterpretations().get(0).getFeature();

        assertEquals(nettunoFeature.getName(), "Nettuno");
        assertEquals(nettunoFeature.getGeometry().getCenter(), new GeocodePoint(41.45907,
                12.66037D));
        assertEquals(response.getInterpretations().get(0).getParents().get(3).getName(), "Italy");

    }

    @Test
    public void givenChicagoShouldReturnProvinceWithCountryAndLatLon() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "Chicago/Brooklyn").addWoeHint(YahooWoeType.ADMIN2).addResponseInclude(
                ResponseIncludes.PARENTS).build();

        final GeocodeResponse response = this.geocoder.geocode(locationRequest);

        LOGGER.info(response + "");

        assertEquals(response.getInterpretations().size(), 2);
        final Feature chicago = response.getInterpretations().get(0).getFeature();

        assertEquals(chicago.getName(), "Kings County");
        assertEquals(chicago.getGeometry().getCenter(), new GeocodePoint(40.63439D, -73.95027D));
        assertEquals(response.getInterpretations().get(0).getParents().get(0).getName(),
                "New York");

    }

    @Test
    public void givenWhenQueryStartsWithHttpShouldThrowException() throws Exception {

        final GeocodeRequest locationRequest = new GeocodeRequest.GeocodeRequestBuilder().query(
                "http")
                .addWoeHint(YahooWoeType.ADMIN2)
                .addResponseInclude(ResponseIncludes.PARENTS)
                .build();

        try {
            final GeocodeResponse response = this.geocoder.geocode(locationRequest);
            fail();
        } catch (GeocoderException e) {
            assertNotNull(e);
            LOGGER.info(e.getMessage());
        }

    }
}
