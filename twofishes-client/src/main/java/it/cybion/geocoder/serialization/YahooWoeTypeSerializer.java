package it.cybion.geocoder.serialization;

import it.cybion.geocoder.requests.YahooWoeType;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class YahooWoeTypeSerializer extends JsonSerializer<YahooWoeType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YahooWoeTypeSerializer.class);

    @Override
    public void serialize(YahooWoeType value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeNumber(value.getValue());
    }
}
