package it.cybion.geocoder.responses;

import it.cybion.geocoder.GeocodeBoundingBox;
import it.cybion.geocoder.GeocodePoint;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class FeatureGeometry {

    private GeocodePoint center;

    private GeocodeBoundingBox bounds;

    // TODO support "well known binary" in a json field. jackson expects base64 encoding, which is not the case
    // only present if we have a polygon for this feature
    //    3: private byte[] wkbGeometry;
    //    4: optional string wktGeometry

    //    5: optional binary wkbGeometrySimplified
    //    6: optional string wktGeometrySimplified
    //
    //    7: optional GeocodeBoundingBox displayBounds
    //
    //    8: optional string source

    private FeatureGeometry() {

    }

    public FeatureGeometry(GeocodePoint center, GeocodeBoundingBox bounds) {

        this.center = center;
        this.bounds = bounds;
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

        FeatureGeometry that = (FeatureGeometry) o;

        if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null) {
            return false;
        }
        if (center != null ? !center.equals(that.center) : that.center != null) {
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

    @Override
    public String toString() {

        return "FeatureGeometry{" +
               "center=" + center +
               ", bounds=" + bounds +
               '}';
    }
}
