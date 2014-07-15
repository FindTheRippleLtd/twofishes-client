package it.cybion.geocoder.serialization;

import it.cybion.geocoder.responses.Flag;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FlagSerializer extends JsonSerializer<Flag> {

    @Override
    public void serialize(Flag value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeNumber(value.getValue());
    }
}
