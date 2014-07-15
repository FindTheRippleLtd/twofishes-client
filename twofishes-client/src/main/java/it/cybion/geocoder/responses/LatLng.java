package it.cybion.geocoder.responses;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class LatLng {

    private float lat;

    private float lng;

    private LatLng() {

    }

    public float getLat() {

        return lat;
    }

    public float getLng() {

        return lng;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LatLng latLng = (LatLng) o;

        if (Float.compare(latLng.lat, lat) != 0) {
            return false;
        }
        if (Float.compare(latLng.lng, lng) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        result = 31 * result + (lng != +0.0f ? Float.floatToIntBits(lng) : 0);
        return result;
    }
}
