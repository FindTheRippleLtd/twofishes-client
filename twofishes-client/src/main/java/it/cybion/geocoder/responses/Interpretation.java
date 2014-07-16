package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Interpretation {

    private String what;

    private String where;

    private Feature feature;

    private Geometry geometry;

    private Interpretation() {

    }

    public String getWhat() {

        return what;
    }

    public String getWhere() {

        return where;
    }

    public Feature getFeature() {

        return feature;
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

        Interpretation that = (Interpretation) o;

        if (feature != null ? !feature.equals(that.feature) : that.feature != null) {
            return false;
        }
        if (geometry != null ? !geometry.equals(that.geometry) : that.geometry != null) {
            return false;
        }
        if (what != null ? !what.equals(that.what) : that.what != null) {
            return false;
        }
        if (where != null ? !where.equals(that.where) : that.where != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = what != null ? what.hashCode() : 0;
        result = 31 * result + (where != null ? where.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Interpretation{" +
               "what='" + what + '\'' +
               ", where='" + where + '\'' +
               ", feature=" + feature +
               ", geometry=" + geometry +
               '}';
    }
}
