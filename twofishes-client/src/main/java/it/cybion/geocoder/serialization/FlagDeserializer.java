package it.cybion.geocoder.serialization;

import it.cybion.geocoder.responses.Flag;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FlagDeserializer extends JsonDeserializer<Flag> {

    @Override
    public Flag deserialize(final JsonParser parser, final DeserializationContext context)
            throws IOException {

        return Flag.fromTypeCode(parser.getValueAsInt());
    }
}
