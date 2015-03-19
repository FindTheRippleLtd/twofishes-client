package it.cybion.geocoder.responses;

import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.serialization.ObjectMapperFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class SerDeTestCase {

    private ObjectMapper objectMapper;

    @BeforeMethod
    public void setUp() throws Exception {

        this.objectMapper = ObjectMapperFactory.INSTANCE.getObjectMapper();

    }

    @AfterMethod
    public void tearDown() throws Exception {

        this.objectMapper = null;

    }

    @Test
    public void fromFile() throws Exception {

        final InputStream geocodeResponseIs = this.getClass().getResourceAsStream(
                "/geocoderesponse.json");

        final GeocodeResponse geocodeResponse = objectMapper.readValue(geocodeResponseIs,
                GeocodeResponse.class);
        assertNotNull(geocodeResponse);
        assertEquals(geocodeResponse.getInterpretations().size(), 1);

    }

    @Test
    public void fromNullFile() throws Exception {

        final InputStream geocodeResponseIs = this.getClass().getResourceAsStream(
                "/geocoderesponse-nulls.json");

        final GeocodeResponse geocodeResponse = objectMapper.readValue(geocodeResponseIs,
                GeocodeResponse.class);
        assertNotNull(geocodeResponse);
        assertEquals(geocodeResponse.getInterpretations().size(), 1);

    }

    @Test
    public void testEnum() throws Exception {

        final FeatureNameFlag abbreviation = FeatureNameFlag.ABBREVIATION;
        final String asJson = objectMapper.writeValueAsString(abbreviation);
        final FeatureNameFlag deserialized = objectMapper.readValue(asJson, FeatureNameFlag.class);
        assertEquals(deserialized, abbreviation);

        final FeatureNameFlag abbreviationFromInt = objectMapper.readValue("2",
                FeatureNameFlag.class);
        assertEquals(abbreviationFromInt, FeatureNameFlag.ABBREVIATION);

        final FeatureNameFlag historicFromInt = objectMapper.readValue("1024",
                FeatureNameFlag.class);
        assertEquals(historicFromInt, FeatureNameFlag.HISTORIC);

        final YahooWoeType admin1 = YahooWoeType.ADMIN1;
        final String admin1AsJson = objectMapper.writeValueAsString(admin1);
        assertEquals(admin1AsJson, "8");

        final YahooWoeType deserializedAdmin1 = objectMapper.readValue(admin1AsJson,
                YahooWoeType.class);
        assertEquals(deserializedAdmin1, admin1);

    }

    @Test
    public void testIncludeParents() throws Exception {

        final InputStream geocodeResponseIs = this.getClass().getResourceAsStream(
                "/include-parents.json");

        final GeocodeResponse geocodeResponse = objectMapper.readValue(geocodeResponseIs,
                GeocodeResponse.class);
        assertNotNull(geocodeResponse);
        assertEquals(geocodeResponse.getInterpretations().size(), 2);
        final Interpretation interpretation = geocodeResponse.getInterpretations().get(0);
        assertEquals(interpretation.getParents().size(), 2);
        assertEquals(interpretation.getParents().get(0).getName(), "Latium");
        assertEquals(interpretation.getParents().get(1).getName(), "Italy");


    }

    @Test
    public void testSourceIsDeserializedIssue6() throws Exception {

        final InputStream geocodeResponseIssue6 = this.getClass().getResourceAsStream(
                "/response-issue-6.json");

        final GeocodeResponse geocodeResponse = objectMapper.readValue(geocodeResponseIssue6,
                GeocodeResponse.class);
        assertNotNull(geocodeResponse);
        assertNotNull(geocodeResponse.getInterpretations());
        assertNotNull(geocodeResponse.getInterpretations().get(0));
        assertNotNull(geocodeResponse.getInterpretations().get(0).getFeature());
        assertNotNull(geocodeResponse.getInterpretations().get(0).getFeature().getIds());

        assertEquals(geocodeResponse.getInterpretations().get(0).getFeature().getIds().get(0)
                .getSource(), "geonameid");
    }
}
