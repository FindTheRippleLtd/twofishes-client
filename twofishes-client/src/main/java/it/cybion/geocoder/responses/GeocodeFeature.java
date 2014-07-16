package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeFeature {

    private String cc;

    private Geometry geometry;

    private GeocodeFeature() {

    }

    public String getCc() {

        return cc;
    }

    public Geometry getGeometry() {

        return geometry;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodeFeature that = (GeocodeFeature) o;

        if (cc != null ? !cc.equals(that.cc) : that.cc != null) {
            return false;
        }
        if (geometry != null ? !geometry.equals(that.geometry) : that.geometry != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = cc != null ? cc.hashCode() : 0;
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "GeocodeFeature{" +
               "cc='" + cc + '\'' +
               ", geometry=" + geometry +
               '}';
    }
}
