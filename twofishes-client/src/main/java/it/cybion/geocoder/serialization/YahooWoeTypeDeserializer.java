package it.cybion.geocoder.serialization;

import it.cybion.geocoder.requests.YahooWoeType;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class YahooWoeTypeDeserializer extends JsonDeserializer<YahooWoeType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YahooWoeTypeDeserializer.class);

    @Override
    public YahooWoeType deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        return YahooWoeType.fromTypeCode(jp.getValueAsInt());
    }
}
