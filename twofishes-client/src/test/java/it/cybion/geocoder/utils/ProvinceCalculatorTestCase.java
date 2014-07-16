package it.cybion.geocoder.utils;

import it.cybion.geocoder.GeocodePoint;
import it.cybion.geocoder.requests.YahooWoeType;
import it.cybion.geocoder.responses.Feature;
import it.cybion.geocoder.responses.FeatureGeometry;
import it.cybion.geocoder.responses.GeocodeFeature;
import it.cybion.geocoder.responses.Interpretation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class ProvinceCalculatorTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceCalculatorTestCase.class);

    @Test
    public void givenFeatureShouldWork() throws Exception {

        final List<Interpretation> someInterpretations = new ArrayList<Interpretation>();
        final GeocodePoint center = new GeocodePoint(10.0D, 10.0D);
        final FeatureGeometry aGeometry = new FeatureGeometry(center, null);
        final Feature aFeature = DataInstances.buildFeature(aGeometry, "a feature name",
                YahooWoeType.ADMIN2);
        final Feature aCountryFeature = DataInstances.buildFeature(aGeometry,
                "a feature country name", YahooWoeType.COUNTRY);
        final Interpretation withFeature = DataInstances.buildInterpretationWithFeature(aFeature);
        final Interpretation withCountry = DataInstances.buildInterpretationWithFeature(
                aCountryFeature);
        someInterpretations.add(withFeature);
        someInterpretations.add(withCountry);
        //end fixtures

        final ProvinceCalculator provinceCalculator = new ProvinceCalculator(someInterpretations);
        final GeocodedProvinceName geocodedProvinceName =
                provinceCalculator.calculateProvinceNameAndLatLon();
        final String countryName = provinceCalculator.calculateCountryName();
        assertEquals(geocodedProvinceName.getName(), "a feature name");
        assertEquals(geocodedProvinceName.getGeolocation(), new GeocodePoint(10.0D, 10.0D));
        assertEquals(countryName, "a feature country name");

    }

    @Test
    public void givenParentsShouldCalculateProvinceNameAndLocation() throws Exception {

        //fixtures
        final GeocodePoint aCenter = new GeocodePoint(1.0D, 1.0D);
        final FeatureGeometry aParentGeometry = new FeatureGeometry(aCenter, null);

        final GeocodeFeature theCountyParent = DataInstances.buildGeocodeFeature(aParentGeometry,
                "the name", YahooWoeType.ADMIN2);

        final GeocodeFeature theCountryParent = DataInstances.buildGeocodeFeature(null,
                "the country name", YahooWoeType.COUNTRY);

        final List<GeocodeFeature> countyAndCountryParents = new ArrayList<GeocodeFeature>();
        countyAndCountryParents.add(theCountyParent);
        countyAndCountryParents.add(theCountryParent);
        final Interpretation emptyWithJustAParent = DataInstances.buildInterpretation(
                countyAndCountryParents);

        final List<Interpretation> someInterpretations = new ArrayList<Interpretation>();
        someInterpretations.add(emptyWithJustAParent);
        //end of fixtures

        final ProvinceCalculator provinceCalculator = new ProvinceCalculator(someInterpretations);
        final GeocodedProvinceName geocodedProvinceName =
                provinceCalculator.calculateProvinceNameAndLatLon();

        assertEquals(geocodedProvinceName.getName(), "the name");
        final GeocodePoint anotherCenter = new GeocodePoint(1.0D, 1.0D);
        assertEquals(geocodedProvinceName.getGeolocation(), anotherCenter);
        assertEquals(provinceCalculator.calculateCountryName(), "the country name");

    }

    private static class DataInstances {

        public static GeocodeFeature buildGeocodeFeature(FeatureGeometry aParentGeometry, String s,
                YahooWoeType admin2) {

            return new GeocodeFeature(null, aParentGeometry, s, null, admin2, null, null, null,
                    null, null, null, null, null, null, null, null, null, null);
        }

        public static Interpretation buildInterpretationWithFeature(Feature feature) {

            return new Interpretation(null, null, feature, null, null, null, null);
        }

        public static Interpretation buildInterpretation(
                List<GeocodeFeature> countyAndCountryParents) {

            return new Interpretation(null, null, null, countyAndCountryParents, null, null, null);
        }

        public static Feature buildFeature(FeatureGeometry aGeometry, String name,
                YahooWoeType aWoeType) {

            return new Feature(null, aGeometry, name, null, aWoeType, null, null, null, null, null,
                    null, null, null, null);
        }
    }
}
