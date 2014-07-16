package it.cybion.geocoder.utils;

import it.cybion.geocoder.GeocodePoint;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.GeocodeFeature;
import it.cybion.geocoder.responses.Interpretation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * TODO this class should be moved out of this module together with GeocodedProvinceName
 *
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class ProvinceCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceCalculator.class);

    private List<Interpretation> interpretations;

    public ProvinceCalculator(final List<Interpretation> interpretations) {

        this.interpretations = interpretations;
    }

    public GeocodedProvinceName calculateProvinceNameAndLatLon() {

        GeocodedProvinceName geocodedProvinceName = GeocodedProvinceName.NULL;

        String name = null;
        GeocodePoint center = null;

        for (final Interpretation interpretation : this.interpretations) {

            final List<GeocodeFeature> parents = interpretation.getParents();

            if (parents != null) {

                for (final GeocodeFeature parent : parents) {
                    if ((parent.getWoeType() != null) &&
                        (parent.getWoeType() == YahooWoeType.ADMIN2)) {
                        name = parent.getName();
                        center = parent.getGeometry().getCenter();
                        break;
                    }
                }
            }
        }

        if (name != null && center != null) {
            geocodedProvinceName = new GeocodedProvinceName(name, center);
        }

        return geocodedProvinceName;
    }

    public String calculateCountryName() {

        String name = null;

        for (final Interpretation interpretation : this.interpretations) {

            final List<GeocodeFeature> parents = interpretation.getParents();

            for (final GeocodeFeature parent : parents) {

                if (parent.getWoeType() == YahooWoeType.COUNTRY) {
                    name = parent.getName();
                    break;
                }
            }
        }

        return name;
    }
}
