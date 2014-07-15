package it.cybion.geocoder;

import it.cybion.geocoder.requests.GeocodeRequest;
import it.cybion.geocoder.responses.GeocodeResponse;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public interface Geocoder {

    public GeocodeResponse geocode(GeocodeRequest request);
}
