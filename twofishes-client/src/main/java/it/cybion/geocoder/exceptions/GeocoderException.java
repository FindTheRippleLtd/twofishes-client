package it.cybion.geocoder.exceptions;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocoderException extends RuntimeException {

    public GeocoderException(String message) {

        super(message);
    }

    public GeocoderException(String s, Exception e) {

        super(s, e);

    }
}
