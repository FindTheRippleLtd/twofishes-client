package it.cybion.geocoder.utils;

import it.cybion.geocoder.GeocodePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodedProvinceName {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodedProvinceName.class);

    public static final GeocodedProvinceName NULL = new GeocodedProvinceName();

    private final String name;

    private final GeocodePoint geolocation;

    private GeocodedProvinceName() {

        this("", null);
    }

    public GeocodedProvinceName(final String name, final GeocodePoint geolocation) {

        this.name = name;
        this.geolocation = geolocation;
    }

    public String getName() {

        return name;
    }

    public GeocodePoint getGeolocation() {

        return geolocation;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodedProvinceName that = (GeocodedProvinceName) o;

        if (geolocation != null ? !geolocation.equals(that.geolocation) :
                that.geolocation != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (geolocation != null ? geolocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "GeocodedProvinceName{" +
               "name='" + name + '\'' +
               ", geolocation=" + geolocation +
               '}';
    }
}
