package it.cybion.geocoder;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeBoundingBox {

    private GeocodePoint ne;

    private GeocodePoint sw;

    private GeocodeBoundingBox() {

    }

    public GeocodeBoundingBox(GeocodePoint ne, GeocodePoint sw) {

        this.ne = ne;
        this.sw = sw;
    }

    public GeocodePoint getNe() {

        return ne;
    }

    public GeocodePoint getSw() {

        return sw;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodeBoundingBox that = (GeocodeBoundingBox) o;

        if (ne != null ? !ne.equals(that.ne) : that.ne != null) {
            return false;
        }
        if (sw != null ? !sw.equals(that.sw) : that.sw != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = ne != null ? ne.hashCode() : 0;
        result = 31 * result + (sw != null ? sw.hashCode() : 0);
        return result;
    }
}
