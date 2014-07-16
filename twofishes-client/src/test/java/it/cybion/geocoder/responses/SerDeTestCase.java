package it.cybion.geocoder.responses;

import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.serialization.FlagDeserializer;
import it.cybion.geocoder.serialization.FlagSerializer;
import it.cybion.geocoder.serialization.YahooWoeTypeDeserializer;
import it.cybion.geocoder.serialization.YahooWoeTypeSerializer;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
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

        this.objectMapper = new ObjectMapper();
        final SimpleModule flagDeserializationModule = new SimpleModule("GeocoderResponseModule",
                new Version(1, 0, 0, null))
                .addDeserializer(Flag.class, new FlagDeserializer())
                .addSerializer(Flag.class, new FlagSerializer())
                .addDeserializer(YahooWoeType.class, new YahooWoeTypeDeserializer())
                .addSerializer(YahooWoeType.class, new YahooWoeTypeSerializer());

        this.objectMapper.registerModule(flagDeserializationModule);

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

        final Flag abbreviation = Flag.ABBREVIATION;
        final String asJson = objectMapper.writeValueAsString(abbreviation);
        final Flag deserialized = objectMapper.readValue(asJson, Flag.class);
        assertEquals(deserialized, abbreviation);

        final Flag abbreviationFromInt = objectMapper.readValue("2", Flag.class);
        assertEquals(abbreviationFromInt, Flag.ABBREVIATION);

        final Flag historicFromInt = objectMapper.readValue("1024", Flag.class);
        assertEquals(historicFromInt, Flag.HISTORIC);

        final YahooWoeType admin1 = YahooWoeType.ADMIN1;
        final String admin1AsJson = objectMapper.writeValueAsString(admin1);
        assertEquals(admin1AsJson, "8");

        final YahooWoeType deserializedAdmin1 = objectMapper.readValue(admin1AsJson,
                YahooWoeType.class);
        assertEquals(deserializedAdmin1, admin1);

    }
}
