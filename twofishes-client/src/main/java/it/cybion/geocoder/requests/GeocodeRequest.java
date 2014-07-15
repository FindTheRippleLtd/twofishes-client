package it.cybion.geocoder.requests;

import it.cybion.geocoder.GeocodeBoundingBox;
import it.cybion.geocoder.GeocodePoint;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class GeocodeRequest {

    private String query;

    // country code hint -- results will be biased towards this country
    private String cc;

    // langugage hint, used to format displayName in response
    private String lang = "en";

    // lat/lng hint -- results will be biased towards this location
    // in revgeo mode, this is the point that is searched for
    private GeocodePoint ll;

    // debug information, currently 0 or 1
    private Integer debug;

    // Is this an autocomplete request? i.e. should we treat this as prefix matching
    private boolean autocomplete;

    // bias the results towards these woe types
    private List<YahooWoeType> woeHint;

    // restrict the results towards these woe types
    private List<YahooWoeType> woeRestrict;

    // supercedes ll for hinting, things in the box get boosted uniformly
    private GeocodeBoundingBox bounds;

    // This can be either a slug or a namespace:id featureid for now
    private String slug;

    // radius in meters, ll+radius is an alternative to boundingbox
    private Integer radius;

    // If set to <= 0, means unlimited in revgeo, and ~3 in geocode or autocomplete
    private Integer maxInterpretations;

    // if set, then restrict to features where the source in one of the ids is in the list
    private List<String> allowedSources;

    // replaces full, includePolygon, wktGeometry, calculateCoverage
    private List<ResponseIncludes> responseIncludes;

    // in geocoding mode, requires all results to fall within the bounds/radius specified
    private boolean strict = false;

    // in autocomplete mode, specifies how strongly locally relevant results are preferred
    private AutocompleteBias autocompleteBias;

    public GeocodeRequest() {

        this(null);

    }

    public GeocodeRequest(String query) {

        this(query, null, "en");

    }

    public GeocodeRequest(String query, String lang) {

        this(query, null, lang);

    }

    public GeocodeRequest(String query, String cc, String lang) {

        this(query, cc, lang, null, 0, false, null, null, null, null, 0, 0, null, null, false,
                AutocompleteBias.BALANCED);

    }

    public GeocodeRequest(String query, String cc, String lang, GeocodePoint ll, Integer debug,
            boolean autocomplete, List<YahooWoeType> woeHint, List<YahooWoeType> woeRestrict,
            GeocodeBoundingBox bounds, String slug, Integer radius, Integer maxInterpretations,
            List<String> allowedSources, List<ResponseIncludes> responseIncludes, boolean strict,
            AutocompleteBias autocompleteBias) {

        this.query = query;
        this.cc = cc;
        this.lang = lang;
        this.ll = ll;
        this.debug = debug;
        this.autocomplete = autocomplete;
        this.woeHint = woeHint;
        this.woeRestrict = woeRestrict;
        this.bounds = bounds;
        this.slug = slug;
        this.radius = radius;
        this.maxInterpretations = maxInterpretations;
        this.allowedSources = allowedSources;
        this.responseIncludes = responseIncludes;
        this.strict = strict;
        this.autocompleteBias = autocompleteBias;
    }

    //TODO add builder

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeocodeRequest that = (GeocodeRequest) o;

        if (autocomplete != that.autocomplete) {
            return false;
        }
        if (strict != that.strict) {
            return false;
        }
        if (allowedSources != null ? !allowedSources.equals(that.allowedSources) :
                that.allowedSources != null) {
            return false;
        }
        if (autocompleteBias != that.autocompleteBias) {
            return false;
        }
        if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null) {
            return false;
        }
        if (cc != null ? !cc.equals(that.cc) : that.cc != null) {
            return false;
        }
        if (debug != null ? !debug.equals(that.debug) : that.debug != null) {
            return false;
        }
        if (lang != null ? !lang.equals(that.lang) : that.lang != null) {
            return false;
        }
        if (ll != null ? !ll.equals(that.ll) : that.ll != null) {
            return false;
        }
        if (maxInterpretations != null ? !maxInterpretations.equals(that.maxInterpretations) :
                that.maxInterpretations != null) {
            return false;
        }
        if (query != null ? !query.equals(that.query) : that.query != null) {
            return false;
        }
        if (radius != null ? !radius.equals(that.radius) : that.radius != null) {
            return false;
        }
        if (responseIncludes != null ? !responseIncludes.equals(that.responseIncludes) :
                that.responseIncludes != null) {
            return false;
        }
        if (slug != null ? !slug.equals(that.slug) : that.slug != null) {
            return false;
        }
        if (woeHint != null ? !woeHint.equals(that.woeHint) : that.woeHint != null) {
            return false;
        }
        if (woeRestrict != null ? !woeRestrict.equals(that.woeRestrict) :
                that.woeRestrict != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (cc != null ? cc.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (ll != null ? ll.hashCode() : 0);
        result = 31 * result + (debug != null ? debug.hashCode() : 0);
        result = 31 * result + (autocomplete ? 1 : 0);
        result = 31 * result + (woeHint != null ? woeHint.hashCode() : 0);
        result = 31 * result + (woeRestrict != null ? woeRestrict.hashCode() : 0);
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        result = 31 * result + (maxInterpretations != null ? maxInterpretations.hashCode() : 0);
        result = 31 * result + (allowedSources != null ? allowedSources.hashCode() : 0);
        result = 31 * result + (responseIncludes != null ? responseIncludes.hashCode() : 0);
        result = 31 * result + (strict ? 1 : 0);
        result = 31 * result + (autocompleteBias != null ? autocompleteBias.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "GeocodeRequest{" +
               "query='" + query + '\'' +
               ", cc='" + cc + '\'' +
               ", lang='" + lang + '\'' +
               ", ll=" + ll +
               ", debug=" + debug +
               ", autocomplete=" + autocomplete +
               ", woeHint=" + woeHint +
               ", woeRestrict=" + woeRestrict +
               ", bounds=" + bounds +
               ", slug='" + slug + '\'' +
               ", radius=" + radius +
               ", maxInterpretations=" + maxInterpretations +
               ", allowedSources=" + allowedSources +
               ", responseIncludes=" + responseIncludes +
               ", strict=" + strict +
               ", autocompleteBias=" + autocompleteBias +
               '}';
    }
}
