package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Bounds {

    private LatLng ne;

    private LatLng sw;

    private Bounds() {

    }

    public LatLng getNe() {

        return ne;
    }

    public LatLng getSw() {

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

        Bounds bounds = (Bounds) o;

        if (ne != null ? !ne.equals(bounds.ne) : bounds.ne != null) {
            return false;
        }
        if (sw != null ? !sw.equals(bounds.sw) : bounds.sw != null) {
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
