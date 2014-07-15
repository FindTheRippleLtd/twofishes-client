package it.cybion.geocoder.responses;

import it.cybion.geocoder.GeocodeBoundingBox;
import it.cybion.geocoder.GeocodePoint;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Geometry {

    private GeocodePoint center;

    private GeocodeBoundingBox bounds;

    //TODO wkbGeometry is missing and simplified

    private Geometry() {

    }

    public GeocodePoint getCenter() {

        return center;
    }

    public GeocodeBoundingBox getBounds() {

        return bounds;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Geometry geometry = (Geometry) o;

        if (bounds != null ? !bounds.equals(geometry.bounds) : geometry.bounds != null) {
            return false;
        }
        if (center != null ? !center.equals(geometry.center) : geometry.center != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = center != null ? center.hashCode() : 0;
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        return result;
    }
}
