package it.cybion.geocoder.serialization;

import it.cybion.geocoder.responses.FeatureNameFlag;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FeatureNameFlagDeserializer extends JsonDeserializer<FeatureNameFlag> {

    @Override
    public FeatureNameFlag deserialize(final JsonParser parser, final DeserializationContext context)
            throws IOException {

        return FeatureNameFlag.fromTypeCode(parser.getValueAsInt());
    }
}
