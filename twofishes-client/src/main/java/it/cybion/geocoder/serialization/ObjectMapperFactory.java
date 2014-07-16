package it.cybion.geocoder.serialization;

import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.FeatureNameFlag;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum ObjectMapperFactory {

    INSTANCE;

    private final ObjectMapper objectMapper;

    private ObjectMapperFactory() {

        final ObjectMapper building = new ObjectMapper();
        final SimpleModule flagDeserializationModule = new SimpleModule("GeocoderResponseModule",
                new Version(1, 0, 0, null)).addDeserializer(FeatureNameFlag.class,
                new FeatureNameFlagDeserializer()).addSerializer(FeatureNameFlag.class,
                new FeatureNameFlagSerializer()).addDeserializer(YahooWoeType.class,
                new YahooWoeTypeDeserializer()).addSerializer(YahooWoeType.class,
                new YahooWoeTypeSerializer());

        building.registerModule(flagDeserializationModule);

        objectMapper = building;

    }

    public ObjectMapper getObjectMapper() {

        return objectMapper;
    }
}
