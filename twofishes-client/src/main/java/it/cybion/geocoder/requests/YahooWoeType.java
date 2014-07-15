package it.cybion.geocoder.requests;

/**
 * comments from http://developer.yahoo.com/geo/geoplanet/guide/concepts.html
 *
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum YahooWoeType {

    UNKNOWN(0),

    // One of the major populated places within a country.
    // This category includes incorporated cities and towns, major unincorporated towns and villages.
    TOWN(7),

    // One of the primary administrative areas within a country.
    // Place type names associated with this place type include:
    // State, Province, Prefecture, Country, Region, Federal District.
    ADMIN1(8),

    // One of the secondary administrative areas within a country.
    // Place type names associated with this place type include:
    // County, Province, Parish, Department, District.
    ADMIN2(9),

    // One of the tertiary administrative areas within a country.
    // Place type names associated with this place type include:
    // Commune, Municipality, District, Ward.
    ADMIN3(10),

    POSTAL_CODE(11),
    COUNTRY(12),
    ISLAND(13),
    AIRPORT(14),
    DRAINAGE(15),
    PARK(16),
    POI(20),

    // One of the subdivisions within a town. This category includes suburbs, neighborhoods, wards.
    SUBURB(22),

    SPORT(23),
    COLLOQUIAL(24),
    ZONE(25),
    HISTORICAL_STATE(26),
    HISTORICAL_COUNTY(27),
    CONTINENT(29),
    TIMEZONE(31),

    HISTORICAL_TOWN(35),

    // UNOFFICIAL
    STREET(100);

    private final int value;

    private YahooWoeType(int value) {

        this.value = value;
    }

    public int getValue() {

        return value;
    }

    @Override
    public String toString() {

        return value + "";
    }
}
