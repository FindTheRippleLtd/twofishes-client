package it.cybion.geocoder;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodePoint {

    private double lat;

    private double lng;

    private GeocodePoint() {

    }

    public GeocodePoint(double lat, double lng) {

        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {

        return lat;
    }

    public double getLng() {

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

        GeocodePoint that = (GeocodePoint) o;

        if (Double.compare(that.lat, lat) != 0) {
            return false;
        }
        if (Double.compare(that.lng, lng) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
