package it.cybion.geocoder.requests;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public enum ResponseIncludes {

    // include as much as we possibly can. Everything below here is true.
    EVERYTHING("EVERYTHING"),
    // include parents in response
    PARENTS("PARENTS"),
    // include all names on base feature
    ALL_NAMES("ALL_NAMES"),
    // include all names on all parents
    PARENT_ALL_NAMES("PARENT_ALL_NAMES"),
    // include geometry in wkb or wkt format if available
    WKB_GEOMETRY("WKB_GEOMETRY"),
    WKT_GEOMETRY("WKT_GEOMETRY"),
    // include geometry coverage information (revgeo only)
    REVGEO_COVERAGE("REVGEO_COVERAGE"),
    // controls if we should fetch parents to construct a string like "New York, NY"
    // for legacy reasons, this is automatically turned on for geocode queries for now.
    // it's mainly here because reverse geocode clients often don't need it
    DISPLAY_NAME("DISPLAY_NAME"),
    // include (11m tolerance simplified) geometry in wkb or wkt format if available
    // make display in json much more pleasant
    WKB_GEOMETRY_SIMPLIFIED("WKB_GEOMETRY_SIMPLIFIED"),
    WKT_GEOMETRY_SIMPLIFIED("WKT_GEOMETRY_SIMPLIFIED");

    private final String value;

    private ResponseIncludes(String value) {

        this.value = value;
    }

    @Override
    public String toString() {

        return this.value;
    }
}
