package it.cybion.geocoder.serialization;

import it.cybion.geocoder.responses.FeatureNameFlag;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FeatureNameFlagSerializer extends JsonSerializer<FeatureNameFlag> {

    @Override
    public void serialize(FeatureNameFlag value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeNumber(value.getValue());
    }
}
