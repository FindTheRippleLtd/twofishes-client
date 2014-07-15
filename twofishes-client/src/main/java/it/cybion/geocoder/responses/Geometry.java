package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Geometry {

    private LatLng center;

    private Bounds bounds;

    private Geometry() {

    }

    public LatLng getCenter() {

        return center;
    }

    public Bounds getBounds() {

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
